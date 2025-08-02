package com.bookcycle.service;

import com.bookcycle.dto.BookRequestDTO;
import com.bookcycle.dto.BookResponseDTO;
import java.util.List;

public interface BookService {
    BookResponseDTO addBook(BookRequestDTO bookRequestDTO);
    List<BookResponseDTO> getAllBooks();
    List<BookResponseDTO> getBooksByGenre(String genre);
    BookResponseDTO updateBook(Long bookId, BookRequestDTO bookRequestDTO);
    void deleteBook(Long bookId);
} 