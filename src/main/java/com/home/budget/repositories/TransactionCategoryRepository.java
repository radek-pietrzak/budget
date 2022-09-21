package com.home.budget.repositories;

import com.home.budget.entities.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long>, JpaSpecificationExecutor<TransactionCategory> {

    TransactionCategory findFirstByName(String categoryName);
}
