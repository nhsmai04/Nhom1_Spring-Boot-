package org.example.spring_jpa.controller;



import jakarta.validation.Valid;
import org.example.spring_jpa.dto.UserDto;
import org.example.spring_jpa.reponses.ResponseObject;
import org.example.spring_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.example.spring_jpa.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping
    public String user(Model model) {
        model.addAttribute("users",userService.getAllUsers());
        return "index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto",userDto);
        return "new";
    }

    @PostMapping("/adduser")
    public String newUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            // Nếu có lỗi validation, bạn có thể trả về một thông báo lỗi hoặc quay lại trang đăng ký
            return "new";
             // Ví dụ: trả về trang đăng ký nếu có lỗi
        }
            userService.registerUser(userDto);
            return "redirect:/users";
    }

    @GetMapping( "/edit/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
       model.addAttribute("user",userService.getUserById(id));
       return "edit";
    }
    @PutMapping("/edituser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id")  int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(ResponseObject.builder()
                        .data(user)
                        .status(HttpStatus.OK)
                        .message("Get user information successfully!")
                        .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject> getUserByFirstName(@RequestParam("firstname") String firstname) {
        User user = userService.getUserByFirstName(firstname);
        return ResponseEntity.ok(ResponseObject.builder()
                .data(user)
                .status(HttpStatus.OK)
                .message("Get user information successfully!")
                .build());
    }



}
