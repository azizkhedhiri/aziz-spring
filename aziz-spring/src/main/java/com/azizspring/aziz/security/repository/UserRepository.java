package com.azizspring.aziz.security.repository;

import com.azizspring.aziz.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<AppUser,String>{


    public  AppUser findAppUserByUsername(String userName);

}
