package com.home.budget.repositories.categories;

import com.home.budget.entities.categories.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.mail.search.SearchTerm;
import java.util.List;
import java.util.Set;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod, Long>, JpaSpecificationExecutor<PayMethod> {

    PayMethod findFirstByPayMethodName(String payMethodName);
    List<PayMethod> findAllByPayMethodNameIn(Set<String> payMethodNames);
}
