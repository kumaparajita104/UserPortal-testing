package org.example.UserPortal.repositories;

import org.example.UserPortal.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Optional<Teacher> getTeacherById(Long id);
    Optional<Teacher> getTeacherByEmail(String email);


}
