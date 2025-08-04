package org.example.spring_jpa.controller;


import jakarta.validation.Valid;
import org.example.spring_jpa.dto.UserDto;
import org.example.spring_jpa.model.User;
import org.example.spring_jpa.reponses.ResponseObject;
import org.example.spring_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userApiService;

    @Autowired
    public UserApiController(UserService userApiService) {
        this.userApiService = userApiService;
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable("id")  int id) {

        userApiService.deleteUserById(id);
        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .message("Delete user successfully!")
                .build());
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable("id")  int id,
                                                     @RequestBody UserDto newUser) {
        Optional<User> updateUser = userApiService.updateUser(id,newUser);

        User resultUser = updateUser.orElseGet(() -> {
            // fallback: tạo mới user
            return   userApiService.createUser(newUser);
        });

        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .message(updateUser.isPresent() ? "Update user successfully!" : "User created because not found!")
                .data(resultUser)
                .build());

    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable("id") int id) {
        User user = userApiService.getUserById(id);

        return ResponseEntity.ok(ResponseObject.builder()
                .data(user)
                .status(HttpStatus.OK)
                .message("Get user information successfully!")
                .build());
    }


    @GetMapping("/search")
    public ResponseEntity<ResponseObject> getUserByFirstName(@RequestParam("firstname") String firstname) {
        User user = userApiService.getUserByFirstName(firstname);
        return ResponseEntity.ok(ResponseObject.builder()
                .data(user)
                .status(HttpStatus.OK)
                .message("Get user information successfully!")
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> registerUser(@RequestBody @Valid UserDto userDto)  {
        userApiService.saveUserDto(userDto);
        return ResponseEntity.ok(ResponseObject.builder()
                .data(userDto)
                .status(HttpStatus.OK)
                .message("Add sucessfully")
                .build());
    }
}
