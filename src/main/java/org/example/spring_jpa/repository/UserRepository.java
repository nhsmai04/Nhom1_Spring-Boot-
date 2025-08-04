package org.example.spring_jpa.repository;

import org.example.spring_jpa.model.User;
import org.springframework.data.domain.Sort;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    boolean existsUserByEmail(String email);
    boolean existsUserByPhone(String phone);
    Optional<User> findUserByLastname(String lastname);
    Optional<User> findUserByFirstname(String lastname);
    Optional<User> findUserByEmail(String email);
    Iterable<User> findAll(Sort name);
    void deleteById(int  id);



}
