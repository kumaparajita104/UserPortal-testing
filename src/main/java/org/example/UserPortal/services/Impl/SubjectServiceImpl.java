package org.example.UserPortal.services.Impl;

import org.example.UserPortal.entity.Student;
import org.example.UserPortal.entity.Subject;
import org.example.UserPortal.entity.Teacher;
import org.example.UserPortal.exception.ResourceNotFoundException;
import org.example.UserPortal.payload.StudentDTO;
import org.example.UserPortal.payload.SubjectDTO;
import org.example.UserPortal.repositories.StudentRepository;
import org.example.UserPortal.repositories.SubjectRepository;
import org.example.UserPortal.repositories.TeacherRepository;
import org.example.UserPortal.services.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService
{

     SubjectRepository subjectRepository;
     StudentRepository studentRepository;
     TeacherRepository teacherRepository;

    ModelMapper modelMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, StudentRepository studentRepository, TeacherRepository teacherRepository,ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public SubjectDTO getInfo(Long sub_id) {
        Subject subject=subjectRepository.findSubjectById(sub_id).orElseThrow(()-> new ResourceNotFoundException("Subject","id",sub_id));
        return maptoDTO(subject);

    }

    @Override
    public SubjectDTO updateInfo(SubjectDTO subjectDTO) {
        Teacher teacher=teacherRepository.findById(subjectDTO.getTeacher_id()).orElseThrow(()-> new ResourceNotFoundException("subject","id",subjectDTO.getTeacher_id()));
        Subject subject=maptoEntity(subjectDTO);
        subject.setName(subjectDTO.getName());
        subject.setTeacher(teacher);
        subject.setMaxCount(subjectDTO.getMaxCount());
        subjectRepository.save(subject);
        return maptoDTO(subject);

    }

    @Override
    public String addSubject(SubjectDTO subjectDTO)
    {
        Subject subject=new Subject();
        subject.setId(subjectDTO.getId());
        subject.setMaxCount(subjectDTO.getMaxCount());
        subject.setName(subjectDTO.getName());
        subjectRepository.save(subject);
        return "Entry successful";

    }

    @Override
    public List<SubjectDTO> getALlSubjects() {
        List<Subject> subjects=subjectRepository.findAll();
        return subjects.stream().map(this::maptoDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudents(Long sub_id)
    {
        Subject subject=subjectRepository.findSubjectById(sub_id).orElseThrow(() -> new ResourceNotFoundException("subject","id",sub_id));
        List<Student> students=subject.getStudents();
        return students.stream().map(this::maptoDTO).collect(Collectors.toList());

    }

    private SubjectDTO maptoDTO(Subject subject)
        {
            return modelMapper.map(subject,SubjectDTO.class);
        }
    private StudentDTO maptoDTO(Student student)
    {
        return modelMapper.map(student,StudentDTO.class);
    }
    private Subject maptoEntity(SubjectDTO subjectDTO)
    {
        return modelMapper.map(subjectDTO,Subject.class);
    }

}
