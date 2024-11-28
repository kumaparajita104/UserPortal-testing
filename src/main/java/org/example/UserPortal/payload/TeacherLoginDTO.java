package org.example.UserPortal.payload;

import lombok.*;

@Getter
@Setter

public class TeacherLoginDTO
{
    public TeacherLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private String email;
    private String password;
}
