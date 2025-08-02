package com.bookcycle.service.impl;

import com.bookcycle.dto.BookRequestDTO;
import com.bookcycle.dto.BookResponseDTO;
import com.bookcycle.model.Book;
import com.bookcycle.model.User;
import com.bookcycle.repository.BookRepository;
import com.bookcycle.service.AuthService;
import com.bookcycle.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthService authService;

    @Override
    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
        User seller = authService.getCurrentUser();
        Book book = new Book();
        // Mapper would be better here
        book.setName(bookRequestDTO.getName());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPublishingYear(bookRequestDTO.getPublishingYear());
        book.setGenre(bookRequestDTO.getGenre());
        book.setPurchasePrice(bookRequestDTO.getPurchasePrice());
        book.setRentalPrice(bookRequestDTO.getRentalPrice());
        book.setStatus(bookRequestDTO.getStatus());
        book.setSeller(seller);
        Book savedBook = bookRepository.save(book);
        return toBookResponseDTO(savedBook);
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toBookResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre).stream()
                .map(this::toBookResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO updateBook(Long bookId, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        // Add authorization check here to ensure only the seller can update
        book.setName(bookRequestDTO.getName());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPublishingYear(bookRequestDTO.getPublishingYear());
        book.setGenre(bookRequestDTO.getGenre());
        book.setPurchasePrice(bookRequestDTO.getPurchasePrice());
        book.setRentalPrice(bookRequestDTO.getRentalPrice());
        book.setStatus(bookRequestDTO.getStatus());
        Book updatedBook = bookRepository.save(book);
        return toBookResponseDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        // Add authorization check here
        bookRepository.deleteById(bookId);
    }

    private BookResponseDTO toBookResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setPublishingYear(book.getPublishingYear());
        dto.setGenre(book.getGenre());
        dto.setPurchasePrice(book.getPurchasePrice());
        dto.setRentalPrice(book.getRentalPrice());
        dto.setStatus(book.getStatus());
        if (book.getSeller() != null) {
            dto.setSellerName(book.getSeller().getName());
        }
        return dto;
    }
} 