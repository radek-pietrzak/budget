package com.home.budget.repositories;

import com.home.budget.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findFirstByAbbreviation(String abbreviation);
}