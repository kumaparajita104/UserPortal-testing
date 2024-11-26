package org.example.UserPortal.payload;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class EnrollDTO
{
    Long id;
    Long student_id;
    Long sub_id;
    int max_cnt;
}
