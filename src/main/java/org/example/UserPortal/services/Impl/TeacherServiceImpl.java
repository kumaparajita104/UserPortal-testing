package org.example.UserPortal.services.Impl;

import org.example.UserPortal.entity.Student;
import org.example.UserPortal.entity.Subject;
import org.example.UserPortal.entity.Teacher;
import org.example.UserPortal.exception.ResourceNotFoundException;
import org.example.UserPortal.exception.UsernameNotFoundException;
import org.example.UserPortal.payload.StudentDTO;
import org.example.UserPortal.payload.SubjectDTO;
import org.example.UserPortal.payload.TeacherDTO;
import org.example.UserPortal.payload.TeacherDisplayDTO;
import org.example.UserPortal.repositories.StudentRepository;
import org.example.UserPortal.repositories.SubjectRepository;
import org.example.UserPortal.repositories.TeacherRepository;
import org.example.UserPortal.services.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    ModelMapper modelMapper;

    public TeacherServiceImpl(SubjectRepository subjectRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubjectDTO getSubject(Long teacher_id) {
        Teacher teacher=teacherRepository.getTeacherById(teacher_id).orElseThrow(()-> new ResourceNotFoundException("teacher","id",teacher_id));
        Subject subject=teacher.getSubject();
        return maptoDTO(subject);
    }

    @Override
    public TeacherDisplayDTO getInfo(Long teacher_id)
    {
        Teacher teacher=teacherRepository.findById(teacher_id).orElseThrow(()->new ResourceNotFoundException("teacher","id",teacher_id));

        return maptoDTO(teacher);
    }

    @Override
    public TeacherDisplayDTO getTeacherByEmail(String email)
    {
        Teacher teacher=teacherRepository.getTeacherByEmail(email).orElseThrow(()->new UsernameNotFoundException("teacher","email",email));
        return maptoDTO(teacher);
    }

    @Override
    public TeacherDisplayDTO addSubject(Long teacher_id, Long sub_id)
    {
        Teacher teacher=teacherRepository.findById(teacher_id).orElseThrow(()->new ResourceNotFoundException("teacher","id",teacher_id));
        Subject subject=subjectRepository.findById(sub_id).orElseThrow(()->new ResourceNotFoundException("subject","id",sub_id));
        teacher.setSubject(subject);
        subject.setTeacher(teacher);
        teacherRepository.save(teacher);
        subjectRepository.save(subject);
        return maptoDTO(teacher);

    }
    @Override
    public List<TeacherDisplayDTO> getAllTeachers()
    {
        List<Teacher> teachers=teacherRepository.findAll();
        return teachers.stream().map(teacher1 -> maptoDTO(teacher1)).collect(Collectors.toList());

    }


    private TeacherDisplayDTO maptoDTO(Teacher teacher)
    {
        TeacherDisplayDTO teacherDisplayDTO=modelMapper.map(teacher,TeacherDisplayDTO.class);
        return teacherDisplayDTO;
    }
    private SubjectDTO maptoDTO(Subject subject)
    {
        SubjectDTO subjectDTO=modelMapper.map(subject,SubjectDTO.class);
        return subjectDTO;
    }
}
