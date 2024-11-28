package org.example.UserPortal.repositories;

import org.example.UserPortal.entity.Student;
import org.example.UserPortal.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long>
{
    List<Subject> getStudentById(Long id);
    Student findStudentById(Long id);

    Optional<Student> findStudentByEmail(String email);


}
