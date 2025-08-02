package com.bookcycle.dto;

import com.bookcycle.model.OrderType;
import lombok.Data;

@Data
public class OrderRequestDTO {
    private Long bookId;
    private OrderType orderType;
} 