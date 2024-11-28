package org.example.UserPortal.services;

import org.example.UserPortal.payload.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService
{
    String registerStudent(StudentDTO studentDTO);
    String registerTeacher(TeacherDTO teacherDTO);

    String registerAdmin(AdminDTO adminDTO);

    String loginTeacher(TeacherLoginDTO teacherLoginDTO);

    String loginStudent(StudentLoginDTO studentLoginDTO);

    String loginAdmin(AdminLoginDTO adminLoginDTO);



}
