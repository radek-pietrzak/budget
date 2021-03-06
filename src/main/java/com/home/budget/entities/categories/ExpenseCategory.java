package com.home.budget.entities.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.budget.entities.main.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "expense_categories")
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @OneToMany(mappedBy = "expenseCategory")
    @JsonIgnore
    private List<Expense> expenses;

    public ExpenseCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
