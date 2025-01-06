package org.example.UserPortal.services.Impl;

import org.example.UserPortal.entity.*;
import org.example.UserPortal.exception.*;
import org.example.UserPortal.payload.*;
import org.example.UserPortal.repositories.*;

import org.example.UserPortal.services.*;
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
        Subject subject=subjectRepository.findSubjectById(subjectDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("subject","id", subjectDTO.getId()));
        Teacher teacher=teacherRepository.getTeacherById(subject.getTeacher().getId()).orElseThrow(() -> new ResourceNotFoundException("teacher","id",subject.getId()));
        Subject subject1=maptoEntity(subjectDTO);
        subject1.setName(subjectDTO.getName());
        subject1.setTeacher(teacher);
        subject1.setMaxCount(subjectDTO.getMaxCount());
        subjectRepository.save(subject1);
        return maptoDTO(subject1);

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
