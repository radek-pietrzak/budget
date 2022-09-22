package com.home.budget.entities;

import com.home.budget.entities.categories.PayMethod;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "transactions")
public class Transaction extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('EXPENSE', 'LONG_EXPENSE', 'INCOME', 'INVESTMENT', 'LOAN')", nullable = false)
    private TransactionType type;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "contractor_id", referencedColumnName = "id", nullable = false)
    private Contractor contractor;
    private BigDecimal amount;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
    private Currency currency;
    private String description;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private TransactionCategory category;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date payDate;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "pay_method_id", referencedColumnName = "id", nullable = false)
    private PayMethod payMethod;
    private boolean isRemoved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transaction that = (Transaction) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
