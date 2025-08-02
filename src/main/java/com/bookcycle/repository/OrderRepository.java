package com.bookcycle.repository;

import com.bookcycle.model.Book;
import com.bookcycle.model.Order;
import com.bookcycle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyer(User buyer);
    List<Order> findByBook_Seller(User seller);
} 