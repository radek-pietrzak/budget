package com.home.budget.entities.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.budget.entities.main.Saving;
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
@Table(name = "saving_categories")
public class SavingCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @OneToMany(mappedBy = "savingCategory")
    @JsonIgnore
    private List<Saving> savings;

    public SavingCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
