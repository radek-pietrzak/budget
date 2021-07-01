package com.home.budget.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "pay_methods")
public class PayMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String payMethodName;
    @OneToMany(mappedBy = "payMethod")
    @JsonIgnore
    private List<Expense> expenses;

    public PayMethod(String payMethodName) {
        this.payMethodName = payMethodName;
    }
}
