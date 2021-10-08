package com.home.budget.entities.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.budget.entities.main.Income;
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
