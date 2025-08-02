package com.bookcycle.controller;

import com.bookcycle.dto.BookRequestDTO;
import com.bookcycle.dto.BookResponseDTO;
import com.bookcycle.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(bookService.addBook(bookRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookResponseDTO>> getBooksByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.getBooksByGenre(genre));
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long bookId, @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(bookService.updateBook(bookId, bookRequestDTO));
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
} 