package com.bottle.team.service.impl;

import com.bottle.team.model.authentication.User;
import com.bottle.team.model.enumaration.Provider;
import com.bottle.team.model.enumaration.Role;
import com.bottle.team.repository.UserRepository;
import com.bottle.team.service.RegistrationMailService;
import com.bottle.team.service.UserService;
import com.bottle.team.validation.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 09/10/2016.
 */
@Validated
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RegistrationMailService registrationMailService;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user = userRepository.save(user);
        return  user;
    }

    @Override
    public User add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setProvider(Provider.LOCAL);
        save(user);
        registrationMailService.sendActivationMail(user, true);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByEmailWithNoPassword(String email) {
        User user = this.findByEmail(email);
        if (user != null) {
            try {
                return user.cloneWithoutPassword();
            } catch (CloneNotSupportedException e) {

            }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.findByEmail(s);

        if (user == null)
            throw new UsernameNotFoundException("User was not found");

        SimpleGrantedAuthority role = new SimpleGrantedAuthority(user.getRole().toString());
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(role);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return findByEmail(email) != null ? true : false;
    }
}