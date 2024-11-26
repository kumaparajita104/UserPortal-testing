package org.example.UserPortal.services;

import org.example.UserPortal.payload.StudentDTO;
import org.example.UserPortal.payload.SubjectDTO;
import org.example.UserPortal.payload.TeacherDTO;
import org.example.UserPortal.payload.TeacherDisplayDTO;

import java.util.List;

public interface TeacherService
{
    SubjectDTO getSubject(Long teacher_id);
    TeacherDisplayDTO getInfo(Long teacher_id);

    TeacherDisplayDTO getTeacherByEmail(String email);

    TeacherDisplayDTO addSubject(Long teacher_id,Long sub_id);

    List<TeacherDisplayDTO> getAllTeachers();







}
