package com.bookcycle.service;

import com.bookcycle.dto.OrderRequestDTO;
import com.bookcycle.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    List<OrderResponseDTO> getMyOrders();
    List<OrderResponseDTO> getMySales();
} 