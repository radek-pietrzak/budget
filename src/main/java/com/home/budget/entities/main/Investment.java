package com.home.budget.entities.main;

import com.home.budget.entities.Auditable;
import com.home.budget.entities.categories.InvestmentCategory;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "investments")
public class Investment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private BigDecimal amount;
    private String currency;
    private String description;
    private LocalDate checkDate;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private InvestmentCategory investmentCategory;

}
