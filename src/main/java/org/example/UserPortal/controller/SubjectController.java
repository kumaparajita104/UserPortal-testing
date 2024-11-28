package org.example.UserPortal.controller;


import org.example.UserPortal.payload.StudentDTO;
import org.example.UserPortal.payload.SubjectDTO;
import org.example.UserPortal.services.SubjectService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @GetMapping("/{sub_id}")
    public ResponseEntity<SubjectDTO> getInfoById(@PathVariable("sub_id")Long sub_id)
    {
        return ResponseEntity.ok(subjectService.getInfo(sub_id));
    }

    @PostMapping("/update")
    public ResponseEntity<SubjectDTO> updateInfo(@RequestBody SubjectDTO subjectDTO)
    {
        SubjectDTO subject = subjectService.updateInfo(subjectDTO);
        return new ResponseEntity<>(subject,HttpStatus.OK);

    }
    @PostMapping("/addSubject")
    public ResponseEntity<String> addSubject(@RequestBody SubjectDTO subjectDTO)
    {
        String str = subjectService.addSubject(subjectDTO);
        return ResponseEntity.ok(str);

    }
    @GetMapping("/allSubjects")
    public List<SubjectDTO> getAllSubjects()
    {
        return subjectService.getALlSubjects();
    }
    @GetMapping("/{id}/allStudents")
    public List<StudentDTO> getAllStudents(@PathVariable("id")Long id)
    {
        return subjectService.getStudents(id);
    }

}
