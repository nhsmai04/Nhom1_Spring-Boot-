package org.example.spring_jpa.service;

import org.example.spring_jpa.dto.UserDto;
import org.example.spring_jpa.model.MyUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService  {

    Iterable<MyUser> getAllUsers();
    Optional<MyUser> updateUser(Integer id , UserDto userDto);

    MyUser createUser(UserDto userDto);

    MyUser getUserById(Integer id);
    MyUser getUserByFirstName(String firstname);
    MyUser getUserByLastName(String lastname);
    MyUser getUserByEmail(String email);


    void saveUserDto(UserDto userDto);
    void saveUser(MyUser myUser);
    void deleteUser(MyUser myUser);
    void deleteUserById(int id);
    void registerUser(UserDto userDto);



}
