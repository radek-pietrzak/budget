package com.home.budget.repositories.categories;

import com.home.budget.entities.categories.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod, Long>, JpaSpecificationExecutor<PayMethod> {

    PayMethod findFirstByPayMethodName(String payMethodName);
}
