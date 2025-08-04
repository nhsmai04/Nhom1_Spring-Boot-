package org.example.spring_jpa.service;

import org.example.spring_jpa.model.Book;
import org.springframework.stereotype.Service;


public interface BookService {
    void saveBook(Book book);
}
