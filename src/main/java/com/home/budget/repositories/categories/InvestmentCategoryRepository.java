package com.home.budget.repositories.categories;

import com.home.budget.entities.categories.InvestmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentCategoryRepository extends JpaRepository<InvestmentCategory, Long>, JpaSpecificationExecutor<InvestmentCategory> {
}
