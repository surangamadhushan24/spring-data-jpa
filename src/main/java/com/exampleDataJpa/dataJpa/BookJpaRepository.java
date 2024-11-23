package com.exampleDataJpa.dataJpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<Book,Long> {
}
