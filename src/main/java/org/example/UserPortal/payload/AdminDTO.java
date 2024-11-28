package org.example.UserPortal.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO
{
    public AdminDTO(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    Long id;
    String name;
    String email;
    String password;
}
