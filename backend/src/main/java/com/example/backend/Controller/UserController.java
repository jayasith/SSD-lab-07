package com.example.backend.Controller;

import com.example.backend.Service.EmailClientService;
import com.example.backend.Service.UserService;
import com.example.backend.Controller.request.UserRequest;
import com.example.backend.model.Person;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class UserController {

    private final UserService userService;
    private final EmailClientService emailClientService;

    @PostMapping("/register")
    public Object saveRegister(@RequestBody UserRequest request) throws NoSuchAlgorithmException {
        if(!request.getUsername().isEmpty() & !request.getPassword().isEmpty()){
            if(request.getUsername().equals("admin")) {
                Person person = Person.builder()
                        .username(request.getUsername())
                        .password(userService.passwordEncryption(request.getPassword()))
                        .role("ADMIN")
                        .build();
                Person responsePerson = userService.saveUser(person);
                return ResponseEntity.ok(responsePerson);
            }else {
                Person person = Person.builder()
                        .username(request.getUsername())
                        .password(userService.passwordEncryption(request.getPassword()))
                        .build();
                Person responsePerson = userService.saveUser(person);
                return ResponseEntity.ok(responsePerson);
            }
        }else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PostMapping("/login")
    public Object saveLogin(@RequestBody UserRequest request) throws NoSuchAlgorithmException {
        if(!request.getUsername().isEmpty() & !request.getPassword().isEmpty()){
            Person responsePerson = userService.getUser(request.getUsername());
            if(responsePerson.getPassword().equals(userService.passwordEncryption(request.getPassword()))){
                return ResponseEntity.ok(responsePerson);
            }else {
                return HttpStatus.BAD_REQUEST;
            }

        }else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PostMapping("/password/reset")
    public Object passwordReset(@RequestBody Person person){
        if(!person.getUsername().isEmpty() & !person.getPassword().isEmpty()){
            Person responsePerson = userService.saveUser(person);
            return ResponseEntity.ok(responsePerson);
        }else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping("/users")
    public List<Person> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/user/{username}")
    public Person getUser(@PathVariable String username){
        return userService.getUser(username);
    }

    @PostMapping("email/verification/{email}")
    public ResponseEntity<String> emailVerification(@PathVariable String email){
        String generatedString = RandomStringUtils.randomAlphanumeric(6);
        String body ="Email verification code: "+generatedString;
        String subject = "Verify your email";
        System.out.println(generatedString);
        emailClientService.sendMail(email,body,subject);

        return ResponseEntity.ok(generatedString);
    }

    @PutMapping("/update")
    public Object updateUser(@RequestBody Person person){
        if(!person.getUsername().isEmpty() & !person.getPassword().isEmpty()){
            Person responsePerson = userService.saveUser(person);
            return ResponseEntity.ok(responsePerson);
        }else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
