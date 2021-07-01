package com.home.budget.repositories;

import com.home.budget.entities.SavingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingCategoryRepository extends JpaRepository<SavingCategory, Long>, JpaSpecificationExecutor<SavingCategory> {
}
