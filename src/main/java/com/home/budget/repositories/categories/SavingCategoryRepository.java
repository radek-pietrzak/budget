package com.home.budget.repositories.categories;

import com.home.budget.entities.categories.SavingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingCategoryRepository extends JpaRepository<SavingCategory, Long>, JpaSpecificationExecutor<SavingCategory> {
}
