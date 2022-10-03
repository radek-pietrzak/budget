package com.home.budget.repositories;

import com.home.budget.entities.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long>, JpaSpecificationExecutor<TransactionCategory> {

    TransactionCategory findFirstByName(String categoryName);
    List<TransactionCategory> findAllByNameIn(Set<String> transactionCategoryNames);
}
