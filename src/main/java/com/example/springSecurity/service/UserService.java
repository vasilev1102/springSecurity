package com.example.springSecurity.service;

import com.example.springSecurity.model.User;
import java.util.List;

public interface UserService {

    List<User> findAll ();
    User getById(long id);
    void save(User user);
    void deleteById(long id);
    User findByUsername(String username);
    void addDefaultUser();
    User passwordCoder(User user);
}