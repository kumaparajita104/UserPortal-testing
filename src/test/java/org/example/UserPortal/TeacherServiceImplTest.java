package org.example.UserPortal;

import org.example.UserPortal.entity.Subject;
import org.example.UserPortal.entity.Teacher;
import org.example.UserPortal.repositories.StudentRepository;
import org.example.UserPortal.services.*;
import org.example.UserPortal.services.Impl.*;
import org.example.UserPortal.exception.ResourceNotFoundException;
import org.example.UserPortal.exception.UsernameNotFoundException;
import org.example.UserPortal.payload.TeacherDisplayDTO;
import org.example.UserPortal.repositories.SubjectRepository;
import org.example.UserPortal.repositories.TeacherRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherServiceImplTest {

    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        teacherRepository = mock(TeacherRepository.class);
        subjectRepository = mock(SubjectRepository.class);
        modelMapper = new ModelMapper();
        teacherService = new TeacherServiceImpl(subjectRepository,studentRepository,teacherRepository,  modelMapper);
    }

    @Test
    void getInfo_TeacherExists_ReturnsTeacherDisplayDTO() {
        // Arrange
        Long teacherId = 1L;
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setName("John Doe");
        teacher.setEmail("john.doe@example.com");

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

        // Act
        TeacherDisplayDTO result = teacherService.getInfo(teacherId);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        verify(teacherRepository, times(1)).findById(teacherId);
    }

    @Test
    void getInfo_TeacherDoesNotExist_ThrowsException() {
        // Arrange
        Long teacherId = 1L;
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> teacherService.getInfo(teacherId));
        verify(teacherRepository, times(1)).findById(teacherId);
    }

    @Test
    void getTeacherByEmail_TeacherExists_ReturnsTeacherDisplayDTO() {
        // Arrange
        String email = "john.doe@example.com";
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("John Doe");
        teacher.setEmail(email);

        when(teacherRepository.getTeacherByEmail(email)).thenReturn(Optional.of(teacher));

        // Act
        TeacherDisplayDTO result = teacherService.getTeacherByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(email, result.getEmail());
        verify(teacherRepository, times(1)).getTeacherByEmail(email);
    }

    @Test
    void getTeacherByEmail_TeacherDoesNotExist_ThrowsException() {
        // Arrange
        String email = "john.doe@example.com";
        when(teacherRepository.getTeacherByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> teacherService.getTeacherByEmail(email));
        verify(teacherRepository, times(1)).getTeacherByEmail(email);
    }


}
