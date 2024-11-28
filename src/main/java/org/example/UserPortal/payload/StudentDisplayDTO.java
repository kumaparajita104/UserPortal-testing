package org.example.UserPortal.payload;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Getter
@Setter


public class StudentDisplayDTO
{
    Long id;
    String name;
    int age;
    private List<SubjectDTO> subjects;
    String email;
}
