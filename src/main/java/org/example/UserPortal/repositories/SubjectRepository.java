package org.example.UserPortal.repositories;

import org.example.UserPortal.entity.Student;
import org.example.UserPortal.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long>
{
    Optional<Subject> findSubjectById(Long id);
}
