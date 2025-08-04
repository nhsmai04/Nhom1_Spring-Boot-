package org.example.spring_jpa.service.impl;

import org.example.spring_jpa.dto.UserDto;
import org.example.spring_jpa.exception.DuplicateFieldException;
import org.example.spring_jpa.exception.NotFoundException;
import org.example.spring_jpa.model.User;
import org.example.spring_jpa.repository.UserRepository;
import org.example.spring_jpa.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<User> getAllUsers()
    {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC,"lastname"));

    }

    @Override
    public User getUserById(Integer id)
    {

        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public User getUserByLastName(String lastname)
    {

        return userRepository.findUserByLastname(lastname).orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public User getUserByFirstName(String firstname)
    {

        return userRepository.findUserByFirstname(firstname).orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public User getUserByEmail(String email)
    {

        return userRepository.findUserByEmail(email).orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public void saveUser(User user)
    {
        userRepository.save(user);
    }

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
        User user = modelMapper.map(userDto, User.class);
        if(user.getCreatedDate()==null)
            user.setCreatedDate(LocalDateTime.now());
        userRepository.save(user);
    }
    @Override
    public void deleteUser(User user)
    {
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(int id)
    {
        if(!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void registerUser(UserDto userDto)
    {
        User user = modelMapper.map(userDto, User.class);
        user.setCreatedDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public User createUser(UserDto userDto)
    {
        User user = modelMapper.map(userDto, User.class);
        user.setCreatedDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(Integer id , UserDto userDto)
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
