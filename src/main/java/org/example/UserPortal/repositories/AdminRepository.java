package org.example.UserPortal.repositories;

import org.example.UserPortal.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long>
{

    Optional<Admin> findAdminById(Long id);
}
