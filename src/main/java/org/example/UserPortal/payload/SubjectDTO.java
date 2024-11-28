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
    public SubjectDTO() {
    }

    public SubjectDTO(Long id, String name, int maxCount, Long teacher_id, List<StudentDTO> students) {
        this.id = id;
        this.name = name;
        this.maxCount = maxCount;
        this.teacher_id = teacher_id;
        this.students = students;
    }

    Long id;
    String name;
    int maxCount;
    Long teacher_id;
    List<StudentDTO> students;
}
