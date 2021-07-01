package com.home.budget.repositories;

import com.home.budget.entities.Investment;
import com.home.budget.entities.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod, Long>, JpaSpecificationExecutor<PayMethod> {
}
