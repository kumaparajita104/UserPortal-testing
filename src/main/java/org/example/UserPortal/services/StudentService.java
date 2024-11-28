package org.example.UserPortal.services;

import org.example.UserPortal.payload.StudentDTO;
import org.example.UserPortal.payload.StudentDisplayDTO;
import org.example.UserPortal.payload.SubjectDTO;

import java.util.List;

public interface StudentService
{
    List<SubjectDTO> getSubjectsById(Long student_id);
    StudentDisplayDTO getInfo(Long student_id);

    StudentDisplayDTO getStudentByEmail(String email);


    StudentDisplayDTO addSubject(StudentDTO studentDTO,Long sub_id);

    List<StudentDisplayDTO> getAllStudents();


}
