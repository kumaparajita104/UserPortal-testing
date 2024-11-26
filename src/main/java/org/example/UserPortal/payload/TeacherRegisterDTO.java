package org.example.UserPortal.payload;

import lombok.*;

@Getter
@Setter

public class TeacherRegisterDTO
{
    Long id;
    String name;
    String email;
    String address;
    int age;
    int sub_id;
}
