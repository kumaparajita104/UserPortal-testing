package org.example.UserPortal.controller;


import org.example.UserPortal.payload.SubjectDTO;

import org.example.UserPortal.payload.TeacherDisplayDTO;
import org.example.UserPortal.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController
{
    TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping("/{email}")
    public ResponseEntity<TeacherDisplayDTO> getTeacherByEmail(@PathVariable(value = "email")String email)
    {

        TeacherDisplayDTO teacherDisplayDto=teacherService.getTeacherByEmail(email);
        return new ResponseEntity<>(teacherDisplayDto, HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<TeacherDisplayDTO> getTeacherById(@PathVariable(value="id")Long id)
    {
        return ResponseEntity.ok(teacherService.getInfo(id));

    }
    @GetMapping("/allTeachers")
    public List<TeacherDisplayDTO> getAllTeachers()
    {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/{teacher_id}/{sub_id}")
    public ResponseEntity<TeacherDisplayDTO> addSubject(@PathVariable(value="teacher_id")Long teacher_id,@PathVariable(value="sub_id")Long sub_id)
    {
        TeacherDisplayDTO teacherDisplayDTO=teacherService.addSubject(teacher_id,sub_id);
        return new ResponseEntity<>(teacherDisplayDTO,HttpStatus.OK);
    }

    @GetMapping("/{teacher_id}")
    public ResponseEntity<SubjectDTO> getSubjectByTeacherId(@PathVariable(value="teacher_id")Long teacher_id)
    {
        SubjectDTO subjectDTO=teacherService.getSubject(teacher_id);
        return ResponseEntity.ok(subjectDTO);
    }

}
