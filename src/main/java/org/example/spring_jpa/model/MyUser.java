package org.example.spring_jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Component
@Getter
@Setter
@Table(name ="users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Integer status = 0;             /*0. Normal 1. Block*/
    private Integer activatedStatus = 0;
    @Column(unique = true) private String email;
    private String phone;
    private String role ;                   /* 0. user 1.admin*/

   // Phân quyền

    private LocalDateTime createdDate;
    public MyUser() {}

    public MyUser(String firstname, String lastname, String username, Integer status, String password, Integer activatedStatus, String email, String phone, String role, LocalDateTime createdDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.status = status;
        this.password = password;
        this.activatedStatus = activatedStatus;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.createdDate = createdDate;
    }


}
