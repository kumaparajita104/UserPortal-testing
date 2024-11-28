package org.example.UserPortal.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginDTO
{
    public AdminLoginDTO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    Long id;
    String email;
    String password;
}
