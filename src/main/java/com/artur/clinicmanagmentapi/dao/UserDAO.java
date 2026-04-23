package com.artur.clinicmanagmentapi.dao;

import com.artur.clinicmanagmentapi.entity.User;

public interface UserDAO {

    public User findByUserName(String userName);
}
