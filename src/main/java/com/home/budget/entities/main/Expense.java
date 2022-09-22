package com.home.budget.entities.main;

import com.home.budget.entities.Auditable;
import com.home.budget.entities.categories.ExpenseCategory;
import com.home.budget.entities.categories.PayMethod;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "expenses")
public class Expense extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private BigDecimal amount;
    private String currency;
    private String description;
    private LocalDate payDate;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "pay_method_id", referencedColumnName = "id")
    private PayMethod payMethod;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ExpenseCategory expenseCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Expense expense = (Expense) o;
        return id != null && Objects.equals(id, expense.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
