package com.home.budget.responses;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExpenseResponse {
    private String id;
    private String user;
    private String amount;
    private String currency;
    private String description;
    private LocalDate payDate;
    private String payMethod;
    private String expenseCategory;
    private String createdUser;
    private String updatedUser;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
