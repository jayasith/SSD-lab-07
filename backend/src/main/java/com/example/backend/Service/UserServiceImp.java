package com.example.backend.Service;

import com.example.backend.model.Person;
import com.example.backend.model.Prole;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public Person saveUser(Person person) {
        return userRepository.save(person);
    }

    @Override
    public List<Person> findAll() {
       return userRepository.findAll();
    }

    @Override
    public Prole saveRole(Prole prole) {
        return roleRepository.save(prole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public Person getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String passwordEncryption(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String sha256hex = Base64.getEncoder().encodeToString(hash);
        return sha256hex;
    }
}
