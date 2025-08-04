package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {
	private final BookRepository bookRepo;

    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return bookRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
    	Book savedBook = bookRepo.save(book);
        return ResponseEntity
                .status(HttpStatus.CREATED) 
                .body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> existing = bookRepo.findById(id);
        if (existing.isPresent()) {
            Book b = existing.get();
            b.setTitle(book.getTitle());
            b.setAuthor(book.getAuthor());
            b.setPublished_day(book.getPublished_day());
            b.setTotal_quanlity(book.getTotal_quanlity());
            bookRepo.save(b);
            return ResponseEntity.ok(b); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found"); // 404
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!bookRepo.existsById(id)) return ResponseEntity.notFound().build();
        bookRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
