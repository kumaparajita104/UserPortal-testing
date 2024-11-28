package org.example.UserPortal;
import org.example.UserPortal.entity.Student;
import org.example.UserPortal.entity.Subject;
import org.example.UserPortal.entity.Teacher;
import org.example.UserPortal.exception.ResourceNotFoundException;
import org.example.UserPortal.payload.StudentDTO;
import org.example.UserPortal.payload.SubjectDTO;
import org.example.UserPortal.repositories.StudentRepository;
import org.example.UserPortal.repositories.SubjectRepository;
import org.example.UserPortal.repositories.TeacherRepository;
import org.example.UserPortal.services.Impl.SubjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubjectServiceImplTest {

    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private ModelMapper modelMapper;
    private SubjectServiceImpl subjectService;

    @BeforeEach
    void setUp() {
        subjectRepository = mock(SubjectRepository.class);
        studentRepository = mock(StudentRepository.class);
        teacherRepository = mock(TeacherRepository.class);
        modelMapper = new ModelMapper();
        subjectService = new SubjectServiceImpl(subjectRepository, studentRepository, teacherRepository, modelMapper);
    }
    @Test
    void updateInfo_TeacherNotFound_ThrowsException() {
        // Arrange
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(1L);
        subjectDTO.setName("Chemistry");
        subjectDTO.setMaxCount(30);
        subjectDTO.setTeacher_id(999L); // Non-existent teacher ID

        when(teacherRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> subjectService.updateInfo(subjectDTO));
        verify(teacherRepository, times(1)).findById(999L);
    }

    @Test
    void maptoDTO_ValidSubject_ReturnsSubjectDTO() {
        // Arrange
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Physics");
        subject.setMaxCount(40);

        // Act
        SubjectDTO result = modelMapper.map(subject, SubjectDTO.class);

        // Assert
        assertNotNull(result);
        assertEquals(subject.getId(), result.getId());
        assertEquals(subject.getName(), result.getName());
        assertEquals(subject.getMaxCount(), result.getMaxCount());
    }



    @Test
    void getInfo_SubjectExists_ReturnsSubjectDTO() {
        // Arrange
        Long subjectId = 1L;
        Subject subject = new Subject(subjectId, "Math", 30, null, null, null);
        when(subjectRepository.findSubjectById(subjectId)).thenReturn(Optional.of(subject));

        // Act
        SubjectDTO result = subjectService.getInfo(subjectId);

        // Assert
        assertNotNull(result);
        assertEquals("Math", result.getName());
        verify(subjectRepository, times(1)).findSubjectById(subjectId);
    }

    @Test
    void getInfo_SubjectDoesNotExist_ThrowsException() {
        // Arrange
        Long subjectId = 1L;
        when(subjectRepository.findSubjectById(subjectId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> subjectService.getInfo(subjectId));
        verify(subjectRepository, times(1)).findSubjectById(subjectId);
    }

    @Test
    void updateInfo_ValidInput_ReturnsUpdatedSubjectDTO() {
        // Arrange
        Long teacherId = 1L;
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(1L);
        subjectDTO.setName("Physics");
        subjectDTO.setMaxCount(50);
        subjectDTO.setTeacher_id(teacherId);

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));
        when(subjectRepository.save(any(Subject.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        SubjectDTO result = subjectService.updateInfo(subjectDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Physics", result.getName());
        assertEquals(50, result.getMaxCount());
        verify(teacherRepository, times(1)).findById(teacherId);
        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    void addSubject_ValidInput_ReturnsSuccessMessage() {
        // Arrange
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(1L);
        subjectDTO.setName("Biology");
        subjectDTO.setMaxCount(40);

        when(subjectRepository.save(any(Subject.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        String result = subjectService.addSubject(subjectDTO);

        // Assert
        assertEquals("Entry successful", result);
        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    void getAllSubjects_ReturnsSubjectList() {
        // Arrange
        Subject subject1 = new Subject(1L, "Math", 30, null, null, null);
        Subject subject2 = new Subject(2L, "Science", 25, null, null, null);
        when(subjectRepository.findAll()).thenReturn(Arrays.asList(subject1, subject2));

        // Act
        List<SubjectDTO> result = subjectService.getALlSubjects();

        // Assert
        assertEquals(2, result.size());
        verify(subjectRepository, times(1)).findAll();
    }



    @Test
    void getStudents_SubjectHasNoStudents_ReturnsEmptyList() {
        // Arrange
        Long subjectId = 1L;
        Subject subject = new Subject();
        subject.setId(subjectId);
        subject.setStudents(Collections.emptyList());
        when(subjectRepository.findSubjectById(subjectId)).thenReturn(Optional.of(subject));

        // Act
        List<StudentDTO> result = subjectService.getStudents(subjectId);

        // Assert
        assertTrue(result.isEmpty());
        verify(subjectRepository, times(1)).findSubjectById(subjectId);
    }
}

