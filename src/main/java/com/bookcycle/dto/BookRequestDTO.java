package com.bookcycle.dto;

import com.bookcycle.model.BookStatus;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class BookRequestDTO {
    private String name;
    private String author;
    private int publishingYear;
    private String genre;
    private BigDecimal purchasePrice;
    private BigDecimal rentalPrice;
    private BookStatus status;
} 