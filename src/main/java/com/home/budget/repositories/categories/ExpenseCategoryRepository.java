package com.home.budget.repositories.categories;

import com.home.budget.entities.categories.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long>, JpaSpecificationExecutor<ExpenseCategory> {
}
