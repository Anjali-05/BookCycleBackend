package com.bookcycle.repository;

import com.bookcycle.model.Book;
import com.bookcycle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenre(String genre);
    List<Book> findBySeller(User seller);
} 