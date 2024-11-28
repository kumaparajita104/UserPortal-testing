package org.example.UserPortal.services;

import org.example.UserPortal.entity.Subject;
import org.example.UserPortal.payload.StudentDTO;
import org.example.UserPortal.payload.SubjectDTO;

import java.util.List;

public interface SubjectService
{    SubjectDTO getInfo(Long sub_id);

    SubjectDTO updateInfo(SubjectDTO subjectDTO);

    String addSubject(SubjectDTO subjectDTO);

    List<SubjectDTO> getALlSubjects();

    List<StudentDTO>  getStudents(Long sub_id);
}
