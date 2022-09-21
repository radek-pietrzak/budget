package com.home.budget.repositories;

import com.home.budget.entities.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {

    Contractor findFirstByFirstName(String firstName);
}