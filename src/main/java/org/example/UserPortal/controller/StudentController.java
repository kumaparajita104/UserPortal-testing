package org.example.UserPortal.controller;

import org.example.UserPortal.payload.StudentDTO;
import org.example.UserPortal.payload.StudentDisplayDTO;
import org.example.UserPortal.payload.SubjectDTO;
import org.example.UserPortal.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<StudentDisplayDTO> getStudentByEmail(@PathVariable(value = "email")String email)
    {

        StudentDisplayDTO studentDisplayDto=studentService.getStudentByEmail(email);
        return new ResponseEntity<>(studentDisplayDto, HttpStatus.OK);
    }

    @GetMapping("/subjects/{id}")
    public List<SubjectDTO> getAllSubjectsById(@PathVariable(value="id")Long id)
    {
        return studentService.getSubjectsById(id);

    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDisplayDTO> getStudentById(@PathVariable(value="id")Long id)
    {
        return ResponseEntity.ok(studentService.getInfo(id));
    }

    @GetMapping("/allStudents")
    public List<StudentDisplayDTO> getAllStudents()
    {
        return studentService.getAllStudents();

    }
    @PostMapping("/addSubject/{sub_id}")
    public ResponseEntity<StudentDisplayDTO> addSubject(@RequestBody StudentDTO studentDto,@PathVariable(name = "sub_id") long id)
    {
        return new ResponseEntity<>(studentService.addSubject(studentDto,id),HttpStatus.OK);
    }


}
