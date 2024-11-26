package org.example.UserPortal.payload;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.UserPortal.entity.Teacher;

import java.util.List;

@Getter
@Setter

public class SubjectDTO
{
    Long id;
    String name;
    int maxCount;
    Long teacher_id;
    List<StudentDTO> students;
}
