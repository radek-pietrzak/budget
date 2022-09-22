package com.home.budget.entities.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.budget.entities.Transaction;
import com.home.budget.entities.main.Expense;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "pay_methods")
public class PayMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String payMethodName;
    @OneToMany(mappedBy = "payMethod")
    @JsonIgnore
    @ToString.Exclude
    private List<Expense> expenses;
    @OneToMany(mappedBy = "payMethod")
    @JsonIgnore
    @ToString.Exclude
    private List<Transaction> transactions;

    public PayMethod(String payMethodName) {
        this.payMethodName = payMethodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PayMethod payMethod = (PayMethod) o;
        return id != null && Objects.equals(id, payMethod.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
