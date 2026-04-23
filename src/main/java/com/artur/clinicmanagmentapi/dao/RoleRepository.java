package com.artur.clinicmanagmentapi.dao;


import com.artur.clinicmanagmentapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
