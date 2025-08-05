package org.example.spring_jpa.controller.web;

import ch.qos.logback.core.model.Model;
import org.example.spring_jpa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public void BookController(BookService bookService) {
        this.bookService = bookService;
    }


   /* @GetMapping
    public String book(Model model) {
        model.addAttribute("book",bookService.getBooks());
        return "books";
    }*/
}
