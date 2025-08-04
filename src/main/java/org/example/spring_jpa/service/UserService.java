package org.example.spring_jpa.service;

import org.example.spring_jpa.dto.UserDto;
import org.example.spring_jpa.model.User;

import java.util.Optional;


public interface UserService {

    Iterable<User> getAllUsers();
    Optional<User> updateUser(Integer id , UserDto userDto);

    User createUser(UserDto userDto);

    User getUserById(Integer id);
    User getUserByFirstName(String firstname);
    User getUserByLastName(String lastname);
    User getUserByEmail(String email);


    void saveUserDto(UserDto userDto);
    void saveUser(User user);
    void deleteUser(User user);
    void deleteUserById(int id);
    void registerUser(UserDto userDto);



}
