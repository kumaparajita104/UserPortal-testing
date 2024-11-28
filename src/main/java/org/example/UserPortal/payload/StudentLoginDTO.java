package org.example.UserPortal.payload;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter

public class StudentLoginDTO
{
    String email;
    String password;

}
