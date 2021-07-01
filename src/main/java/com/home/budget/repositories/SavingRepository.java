package com.home.budget.repositories;

import com.home.budget.entities.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long>, JpaSpecificationExecutor<Saving> {
}
