package com.home.budget.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "income_categories")
public class IncomeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @OneToMany(mappedBy = "incomeCategory")
    @JsonIgnore
    private List<Income> incomes;

    public IncomeCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
