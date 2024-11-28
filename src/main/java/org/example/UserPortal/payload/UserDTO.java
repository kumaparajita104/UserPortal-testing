package org.example.UserPortal.payload;

import lombok.*;

@Getter
@Setter

public class UserDTO
{
    Long id;
    String name;
    String email;
    String address;
    int age;
    int exp;
    String qualification;
    String classLevel;
    String password;
    int role_id;
}
