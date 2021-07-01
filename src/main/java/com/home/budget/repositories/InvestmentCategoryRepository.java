package com.home.budget.repositories;

import com.home.budget.entities.InvestmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentCategoryRepository extends JpaRepository<InvestmentCategory, Long>, JpaSpecificationExecutor<InvestmentCategory> {
}
