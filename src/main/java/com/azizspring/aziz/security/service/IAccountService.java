package com.azizspring.aziz.security.service;

import com.azizspring.aziz.security.entities.AppRole;
import com.azizspring.aziz.security.entities.AppUser;

public interface IAccountService {

    public void addRole(String role);
    public void addUser(String username,String password,String mail);
    public void addroleToUser(String username,String role);
    public AppUser loadUserByUserName(String username);
}

