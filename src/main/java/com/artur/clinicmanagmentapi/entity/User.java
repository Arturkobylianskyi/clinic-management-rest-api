package com.artur.clinicmanagmentapi.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String userName;

    @Column(name="enable")
    private boolean enable;

    @Column(name="password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;


    public User(){}

    public User(String password, boolean enable, String userName) {
        this.password = password;
        this.enable = enable;
        this.userName = userName;
    }

    public User(String userName, boolean enable, String password, Collection<Role> roles) {
        this.userName = userName;
        this.enable = enable;
        this.password = password;
        this.roles = roles;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
