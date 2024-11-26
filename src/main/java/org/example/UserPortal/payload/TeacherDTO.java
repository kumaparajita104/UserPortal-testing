package org.example.UserPortal.payload;

import lombok.*;

@Getter
@Setter

public class TeacherDTO
{
    public TeacherDTO(Long id, String name, Long sub_id, String email, String address, int age, int exp, String qualification, String password) {
        this.id = id;
        this.name = name;
        this.sub_id = sub_id;
        this.email = email;
        this.address = address;
        this.age = age;
        this.exp = exp;
        this.qualification = qualification;
        this.password = password;
    }

    Long id;
    String name;
    Long sub_id;
    String email;
    String address;
    int age;
    int exp;
    String qualification;
    String password;


}
