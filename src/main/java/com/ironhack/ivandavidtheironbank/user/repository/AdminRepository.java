package com.ironhack.ivandavidtheironbank.user.repository;

import com.ironhack.ivandavidtheironbank.user.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
