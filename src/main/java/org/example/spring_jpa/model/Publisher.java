package org.example.spring_jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "publishers")
@Getter
@Setter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> books;
    public Publisher() {}
    public Publisher(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

}
