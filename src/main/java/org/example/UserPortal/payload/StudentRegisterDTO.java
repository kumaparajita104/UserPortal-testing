package org.example.UserPortal.payload;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter

public class StudentRegisterDTO
{
    Long id;
    String name;
    String email;
    String address;
    int age;
    String classLevel;
}
