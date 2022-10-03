package com.home.budget.repositories;

import com.home.budget.entities.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {

    Contractor findFirstByFirstName(String firstName);
    List<Contractor> findAllByFirstNameIn(Set<String> firstNames);
}
