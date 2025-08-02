package com.bookcycle.dto;

import com.bookcycle.model.OrderType;
import lombok.Data;
import java.time.LocalDate;

@Data
public class OrderResponseDTO {
    private Long id;
    private BookResponseDTO book;
    private String buyerName;
    private OrderType orderType;
    private LocalDate orderDate;
    private LocalDate returnDate;
} 