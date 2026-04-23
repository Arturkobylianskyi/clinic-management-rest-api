package com.artur.clinicmanagmentapi.service;

import com.artur.clinicmanagmentapi.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);
}
