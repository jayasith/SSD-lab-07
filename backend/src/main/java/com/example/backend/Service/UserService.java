package com.example.backend.Service;

import com.example.backend.model.Prole;
import com.example.backend.model.Person;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    Person saveUser(Person person);

    List<Person> findAll();
    Prole saveRole(Prole prole);
    void addRoleToUser(String username, String roleName);
    Person getUser(String username);
    String passwordEncryption(String password) throws NoSuchAlgorithmException;
}
