package com.azizspring.aziz.security.repository;

import com.azizspring.aziz.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<AppRole,String > {
}

