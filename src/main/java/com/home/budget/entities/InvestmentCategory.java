package com.home.budget.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
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
