package org.example.UserPortal.services.Impl;

import org.example.UserPortal.entity.*;
import org.example.UserPortal.exception.ResourceNotFoundException;
import org.example.UserPortal.exception.UsernameNotFoundException;
import org.example.UserPortal.payload.*;
import org.example.UserPortal.repositories.*;
import org.example.UserPortal.services.*;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    SubjectRepository subjectRepository;
    StudentRepository studentRepository;
    TeacherRepository teacherRepository;

    UserRepository userRepository;

    RoleRepository roleRepository;

    AdminRepository adminRepository;

    public UserServiceImpl(SubjectRepository subjectRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, ModelMapper modelMapper,RoleRepository roleRepository,UserRepository userRepository,AdminRepository adminRepository) {

        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.adminRepository=adminRepository;
    }

    ModelMapper modelMapper;
    @Override
    public String registerStudent(StudentDTO studentDTO) {
        User user = new User();
        user.setId(studentDTO.getId());
        user.setName(studentDTO.getName());

        user.setAge(studentDTO.getAge());
        user.setEmail(studentDTO.getEmail());
        user.setPassword(studentDTO.getPassword());
        user.setAddress(studentDTO.getAddress());
        user.setClassLevel(studentDTO.getClassLevel());
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("Student").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setEmail(studentDTO.getEmail());

        student.setAddress(studentDTO.getAddress());
        student.setClassLevel(studentDTO.getClassLevel());
        student.setUser(user);
        user.setStudent(student);

        studentRepository.save(student);

        return "You have registered successfully";
    }

    @Override
    public String registerTeacher(TeacherDTO teacherDTO) {
        User user = new User();
        user.setId(teacherDTO.getId());
        user.setName(teacherDTO.getName());

        user.setAge(teacherDTO.getAge());
        user.setEmail(teacherDTO.getEmail());
        user.setPassword(teacherDTO.getPassword());
        user.setAddress(teacherDTO.getAddress());
        user.setExp(teacherDTO.getExp());
        user.setQualification(teacherDTO.getQualification());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("Teacher").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());

        teacher.setAge(teacherDTO.getAge());
        teacher.setEmail(teacherDTO.getEmail());

        teacher.setAddress(teacherDTO.getAddress());
        teacher.setExp(teacherDTO.getExp());
        teacher.setQualification(teacherDTO.getQualification());
        teacher.setUser(user);
        user.setTeacher(teacher);


        teacherRepository.save(teacher);

        return "You have registered successfully";
    }

    @Override
    public String registerAdmin(AdminDTO adminDTO) {
        User user=new User();
        user.setId(adminDTO.getId());
        user.setName(adminDTO.getName());


        user.setEmail(adminDTO.getEmail());
        user.setPassword(adminDTO.getPassword());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("Admin").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        Admin admin=new Admin();
        admin.setId(adminDTO.getId());
        admin.setName(adminDTO.getName());


        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        admin.setUser(user);
        adminRepository.save(admin);
        return "Registration successfull";



    }

    @Override
    public String loginTeacher(TeacherLoginDTO teacherLoginDTO) {
        Teacher teacher=teacherRepository.getTeacherByEmail(teacherLoginDTO.getEmail()).orElseThrow(()->new UsernameNotFoundException("teacher","email",teacherLoginDTO.getEmail()));
        User user=teacher.getUser();
        if(Objects.equals(teacherLoginDTO.getPassword(), user.getPassword()))
            return "Login Successful";
        else
        {
            return "Invalid credentials";
        }
    }

    @Override
    public String loginStudent(StudentLoginDTO studentLoginDTO) {
        Student student=studentRepository.findStudentByEmail(studentLoginDTO.getEmail()).orElseThrow(()->new UsernameNotFoundException("student","email",studentLoginDTO.getEmail()));
        User user=student.getUser();
        if(Objects.equals(studentLoginDTO.getPassword(), user.getPassword()))
            return "Login Successful";
        else
        {
            return "Invalid credentials";
        }
    }

    @Override
    public String loginAdmin(AdminLoginDTO adminLoginDTO)
    {
        Admin admin=adminRepository.findAdminById(adminLoginDTO.getId()).orElseThrow(()->new ResourceNotFoundException("admin","id",adminLoginDTO.getId()));
        User user=admin.getUser();
        if(Objects.equals(user.getEmail(), adminLoginDTO.getEmail()) && Objects.equals(user.getPassword(),adminLoginDTO.getPassword()))
            return "Login Successful";
        else return "Login unsuccessful";

    }
}
