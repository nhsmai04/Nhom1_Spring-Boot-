package org.example.spring_jpa.service.impl;

import org.example.spring_jpa.dto.UserDto;
import org.example.spring_jpa.exception.DuplicateFieldException;
import org.example.spring_jpa.exception.NotFoundException;
import org.example.spring_jpa.model.MyUser;
import org.example.spring_jpa.repository.UserRepository;
import org.example.spring_jpa.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    // Authentication login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<MyUser> user = userRepository.findUserByUsername(username);

        if(user.isPresent()){
            var userObj =  user.get();

            Set<GrantedAuthority> authorities = new HashSet<>();

            if(userObj.getRole().equals("ADMIN")){
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }else if(userObj.getRole().equals("USER")){
                authorities.add(new SimpleGrantedAuthority("publishers:read"));
            }

            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .authorities(authorities)
                    .build();
        }else
            throw new UsernameNotFoundException("Username not found");

    }
        private String[] getRoles(MyUser user){
            if(user.getRole() == null)
                return new String[]{"USER"};
            return user.getRole().split(",");
        }


    /*User features*/

    @Override
    public Iterable<MyUser> getAllUsers()
    {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC,"lastname"));

    }

    @Override
    public MyUser getUserById(Integer id)
    {

        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public MyUser getUserByLastName(String lastname)
    {

        return userRepository.findUserByLastname(lastname).orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public MyUser getUserByFirstName(String firstname)
    {

        return userRepository.findUserByFirstname(firstname).orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public MyUser getUserByEmail(String email)
    {

        return userRepository.findUserByEmail(email).orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public void saveUser(MyUser myUser)
    {

        if(myUser.getRole() == null)
            myUser.setRole("USER");
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));


        userRepository.save(myUser);
    }
    // Tạo người dùng thông bằng restcontroller thông qua Api
    @Override
    public void saveUserDto(UserDto userDto)
    {
        Map<String, String> errors = new HashMap<>();

        if (userRepository.existsUserByEmail(userDto.getEmail())) {
            errors.put("email", "Email already exists");
        }

        if (userRepository.existsUserByPhone(userDto.getPhone())) {
            errors.put("phone", "Phone already exists");
        }

        if (!errors.isEmpty()) {
            throw new DuplicateFieldException(errors);
        }
        MyUser myUser = modelMapper.map(userDto, MyUser.class);
        if(myUser.getCreatedDate()==null)
            myUser.setCreatedDate(LocalDateTime.now());
        saveUser(myUser);
    }
    @Override
    public void deleteUser(MyUser myUser)
    {
        userRepository.delete(myUser);
    }

    @Override
    public void deleteUserById(int id)
    {
        if(!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
    // đăng ký tạo người dùng sử dụng cho form
    @Override
    public void registerUser(UserDto userDto)
    {
        MyUser myUser = modelMapper.map(userDto, MyUser.class);
        myUser.setCreatedDate(LocalDateTime.now());
        saveUser(myUser);
    }

    @Override
    public MyUser createUser(UserDto userDto)
    {
        MyUser myUser = modelMapper.map(userDto, MyUser.class);
        myUser.setCreatedDate(LocalDateTime.now());
        return userRepository.save(myUser);
    }

    @Override
    public Optional<MyUser> updateUser(Integer id , UserDto userDto)
    {
        Map<String, String> errors = new HashMap<>();

        if(userRepository.existsUserByEmail(userDto.getEmail())) {
            errors.put("email", "Email already exists");
        }
        if(userRepository.existsUserByPhone(userDto.getPhone())) {
            errors.put("phone", "Phone already exists");
        }
        if (!errors.isEmpty()) {
            throw new DuplicateFieldException(errors);
        }
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setLastname(userDto.getLastname());
                    existingUser.setFirstname(userDto.getFirstname());
                    existingUser.setUsername(userDto.getUsername());
                    existingUser.setPhone(userDto.getPhone());
                    existingUser.setEmail(userDto.getEmail());
                    existingUser.setPassword(userDto.getPassword());
                    return userRepository.save(existingUser);
                });
    }
}
