package com.home.budget.repositories;

import com.home.budget.entities.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Long>, JpaSpecificationExecutor<IncomeCategory> {
}
