package com.artur.clinicmanagmentapi.dao;

import com.artur.clinicmanagmentapi.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> theQuery = entityManager.createQuery(
                "from User where userName=:uName and enabled=1", User.class);

        theQuery.setParameter("uName", userName);

        User theUser = null;
        try{
            theUser = theQuery.getSingleResult();
        } catch (Exception e){
            theUser = null;
        }

        return theUser;
    }

}
