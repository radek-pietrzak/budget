package com.home.budget.repositories;

import com.home.budget.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findFirstByAbbreviation(String abbreviation);
    List<Currency> findAllByAbbreviationIn(Set<String> abbreviation);
}
