package com.bookcycle.controller;

import com.bookcycle.dto.OrderRequestDTO;
import com.bookcycle.dto.OrderResponseDTO;
import com.bookcycle.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('BUYER')")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok(orderService.createOrder(orderRequestDTO));
    }

    @GetMapping("/my-orders")
    @PreAuthorize("hasAuthority('BUYER')")
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders() {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

    @GetMapping("/my-sales")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<List<OrderResponseDTO>> getMySales() {
        return ResponseEntity.ok(orderService.getMySales());
    }
} 