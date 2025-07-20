package com.irem.demo.repository;

import com.irem.demo.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonTypeRepository extends JpaRepository<PersonType, Long> {
    Optional<PersonType> findByName(String name);
}