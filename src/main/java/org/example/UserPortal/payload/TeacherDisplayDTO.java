package org.example.UserPortal.payload;

import lombok.*;

@Getter
@Setter

public class TeacherDisplayDTO
{
    Long id;
    String name;
    String email;
    Long sub_id;

    public TeacherDisplayDTO() {
    }

    public TeacherDisplayDTO(Long id, String name, String email, Long s_id)
    {
        this.id=id;
        this.name=name;
        this.email=email;
        this.sub_id=s_id;

    }
}
