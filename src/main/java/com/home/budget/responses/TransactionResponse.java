package com.home.budget.responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TransactionResponse {
    private String id;
    private String contractor;
    private String amount;
    private String currency;
    private String description;
    private Date payDate;
    private String payMethod;
    private String transactionCategory;
    private String createdUser;
    private String updatedUser;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
