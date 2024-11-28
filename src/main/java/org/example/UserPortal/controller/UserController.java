package org.example.UserPortal.controller;


import org.example.UserPortal.payload.*;
import org.example.UserPortal.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portal")
public class UserController
{
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/student/register")
    public String registerStudent(@RequestBody StudentDTO studentDTO)
    {
        return userService.registerStudent(studentDTO);
    }

    @PostMapping("/teacher/register")
    public String registerTeacher(@RequestBody TeacherDTO teacherDTO)
    {
        return userService.registerTeacher(teacherDTO);
    }

    @PostMapping("/teacher/login")
    public String loginTeacher(@RequestBody TeacherLoginDTO teacherLoginDTO)
    {
        return userService.loginTeacher(teacherLoginDTO);
    }

    @PostMapping("/student/login")
    public String loginStudent(@RequestBody StudentLoginDTO studentLoginDTO)
    {
        return userService.loginStudent(studentLoginDTO);
    }
    @PostMapping("/admin/login")
    public String loginAdmin(@RequestBody AdminLoginDTO adminLoginDTO)
    {
        return userService.loginAdmin(adminLoginDTO);
    }
    @PostMapping("/admin/register")
    public String registerAdmin(@RequestBody AdminDTO adminDTO)
    {
        return userService.registerAdmin(adminDTO);
    }

}
