package com.home.budget.entities.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.budget.entities.main.Investment;
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
@Table(name = "investment_categories")
public class InvestmentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @OneToMany(mappedBy = "investmentCategory")
    @JsonIgnore
    private List<Investment> investments;

    public InvestmentCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
