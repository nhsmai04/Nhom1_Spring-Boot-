package org.example.spring_jpa.repository;

import org.example.spring_jpa.model.MyUser;
import org.springframework.data.domain.Sort;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<MyUser,Integer> {

    boolean existsUserByEmail(String email);
    boolean existsUserByPhone(String phone);
    Optional<MyUser> findUserByLastname(String lastname);
    Optional<MyUser> findUserByFirstname(String lastname);
    Optional<MyUser> findUserByEmail(String email);
    Optional<MyUser> findUserByUsername(String username);
    Iterable<MyUser> findAll(Sort name);
    void deleteById(int  id);



}
