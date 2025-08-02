package com.bookcycle.service.impl;

import com.bookcycle.dto.BookResponseDTO;
import com.bookcycle.dto.OrderRequestDTO;
import com.bookcycle.dto.OrderResponseDTO;
import com.bookcycle.model.Book;
import com.bookcycle.model.Order;
import com.bookcycle.model.OrderType;
import com.bookcycle.model.User;
import com.bookcycle.repository.BookRepository;
import com.bookcycle.repository.OrderRepository;
import com.bookcycle.service.AuthService;
import com.bookcycle.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthService authService;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        User buyer = authService.getCurrentUser();
        Book book = bookRepository.findById(orderRequestDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Order order = new Order();
        order.setBuyer(buyer);
        order.setBook(book);
        order.setOrderType(orderRequestDTO.getOrderType());
        order.setOrderDate(LocalDate.now());

        if (order.getOrderType() == OrderType.RENT) {
            order.setReturnDate(LocalDate.now().plusDays(30)); // 30-day rental period
        }

        Order savedOrder = orderRepository.save(order);
        return toOrderResponseDTO(savedOrder);
    }

    @Override
    public List<OrderResponseDTO> getMyOrders() {
        User buyer = authService.getCurrentUser();
        return orderRepository.findByBuyer(buyer).stream()
                .map(this::toOrderResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDTO> getMySales() {
        User seller = authService.getCurrentUser();
        return orderRepository.findByBook_Seller(seller).stream()
                .map(this::toOrderResponseDTO)
                .collect(Collectors.toList());
    }

    private OrderResponseDTO toOrderResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setBook(toBookResponseDTO(order.getBook()));
        dto.setBuyerName(order.getBuyer().getName());
        dto.setOrderType(order.getOrderType());
        dto.setOrderDate(order.getOrderDate());
        dto.setReturnDate(order.getReturnDate());
        return dto;
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