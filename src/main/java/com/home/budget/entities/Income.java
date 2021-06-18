package com.home.budget.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private BigDecimal amount;
    private String currency;
    private String description;
    private LocalDate payDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private IncomeCategory incomeCategory;

}
