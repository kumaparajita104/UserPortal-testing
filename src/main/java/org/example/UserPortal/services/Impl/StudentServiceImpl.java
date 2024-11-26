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

    private StudentRepository studentRepository;

    private SubjectRepository subjectRepository;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

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
        return subjects.stream().map(subject1 -> maptoDto(subject1)).collect(Collectors.toList());



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

        StudentDisplayDTO studentDTO1=maptoDto(student);
        return studentDTO1;
    }

    @Override
    public List<StudentDisplayDTO> getAllStudents()
    {
        List<Student> students=studentRepository.findAll();
        return students.stream().map(student1 -> maptoDto(student1)).collect(Collectors.toList());

    }




    private SubjectDTO maptoDto(Subject subject)
    {
        SubjectDTO subjectDTO=modelMapper.map(subject, SubjectDTO.class);
        return subjectDTO;
    }
    private StudentDisplayDTO maptoDto(Student student)
    {
        StudentDisplayDTO studentDisplayDTO=modelMapper.map(student,StudentDisplayDTO.class);
        return studentDisplayDTO;
    }

    private Student maptoEntity(StudentDTO studentDTO)
    {
        Student student=modelMapper.map(studentDTO,Student.class);
        return student;
    }
}
