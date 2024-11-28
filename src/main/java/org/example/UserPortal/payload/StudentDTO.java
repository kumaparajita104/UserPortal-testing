package org.example.UserPortal.payload;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.UserPortal.entity.Subject;

import java.util.List;

@Getter
@Setter


public class StudentDTO
{
    public StudentDTO(Long id, String name, int age, List<SubjectDTO> subjects, String email, String password, String classLevel, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.subjects = subjects;
        this.email = email;
        this.password = password;
        this.classLevel = classLevel;
        this.address = address;
    }

    Long id;
    String name;
    int age;
    List<SubjectDTO> subjects;
    String email;
    String password;
    String classLevel;
    String address;
}
