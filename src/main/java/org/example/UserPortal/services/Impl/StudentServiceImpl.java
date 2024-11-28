package org.example.UserPortal.services.Impl;

import org.example.UserPortal.entity.Student;
import org.example.UserPortal.entity.Subject;
import org.example.UserPortal.exception.ResourceNotFoundException;
import org.example.UserPortal.exception.UsernameNotFoundException;
import org.example.UserPortal.payload.StudentDTO;
import org.example.UserPortal.payload.StudentDisplayDTO;
import org.example.UserPortal.payload.SubjectDTO;
import org.example.UserPortal.repositories.RoleRepository;
import org.example.UserPortal.repositories.StudentRepository;
import org.example.UserPortal.repositories.SubjectRepository;
import org.example.UserPortal.repositories.UserRepository;
import org.example.UserPortal.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.exit;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    SubjectRepository subjectRepository;

    UserRepository userRepository;

    RoleRepository roleRepository;

    ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository, RoleRepository roleRepository, ModelMapper modelMapper,UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.userRepository=userRepository;
    }

    @Override
    public List<SubjectDTO> getSubjectsById(Long student_id)
    {
        List<Subject> subjects=studentRepository.getStudentById(student_id);
        return subjects.stream().map(this::maptoDto).collect(Collectors.toList());



    }

    @Override
    public StudentDisplayDTO getInfo(Long student_id) {
        Student student=studentRepository.findStudentById(student_id);
        return maptoDto(student);
    }

    @Override
    public StudentDisplayDTO getStudentByEmail(String email) {
        Student student=studentRepository.findStudentByEmail(email).orElseThrow(()->new UsernameNotFoundException("student","email",email));
        return maptoDto(student);
    }

    @Override
    public StudentDisplayDTO addSubject(StudentDTO studentDTO, Long sub_id) {
        Student student=maptoEntity(studentDTO);
        Subject subject=subjectRepository.findSubjectById(sub_id).orElseThrow(() -> new ResourceNotFoundException("Subject","Id",sub_id));
        int maxcnt=subject.getMaxCount();
        if(maxcnt==0)
        {
            System.out.println("No seats available");
            exit(0);
        }
        maxcnt=maxcnt-1;

        List<Subject> subjects=student.getSubjects();
        subjects.add(subject);
        subject.setMaxCount(maxcnt);
        subject.setStudent(student);
        student.setSubjects(subjects);

        studentRepository.save(student);
        subjectRepository.save(subject);

        return maptoDto(student);
    }

    @Override
    public List<StudentDisplayDTO> getAllStudents()
    {
        List<Student> students=studentRepository.findAll();
        return students.stream().map(this::maptoDto).collect(Collectors.toList());

    }




    private SubjectDTO maptoDto(Subject subject)
    {
        return modelMapper.map(subject, SubjectDTO.class);
    }
    private StudentDisplayDTO maptoDto(Student student)
    {
        return modelMapper.map(student,StudentDisplayDTO.class);
    }

    private Student maptoEntity(StudentDTO studentDTO)
    {
        return modelMapper.map(studentDTO,Student.class);
    }
}
