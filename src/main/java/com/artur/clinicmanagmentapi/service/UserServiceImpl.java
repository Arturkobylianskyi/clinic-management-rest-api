package com.artur.clinicmanagmentapi.service;

import com.artur.clinicmanagmentapi.dao.RoleRepository;
import com.artur.clinicmanagmentapi.dao.UserDAO;
import com.artur.clinicmanagmentapi.entity.Role;
import com.artur.clinicmanagmentapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleRepository roleRepository) {
        this.userDAO = userDAO;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        System.out.println("\n=== [ШПИГУН] ШУКАЮ КОРИСТУВАЧА: " + userName + " ===");
        User user = userDAO.findByUserName(userName);

        if(user==null){
            System.out.println("=== [ШПИГУН] ПОМИЛКА: КОРИСТУВАЧА НЕ ЗНАЙДЕНО В БАЗІ! ===");
            throw new UsernameNotFoundException("invalid username or password");
        }

        System.out.println("=== [ШПИГУН] ЗНАЙДЕНО: " + user.getUserName() + " ===");
        System.out.println("=== [ШПИГУН] ХЕШ В БАЗІ: " + user.getPassword() + " ===");

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role->new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
