package com.exampleDataJpa.dataJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StudentJpaRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s  FROM Student s  WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email) ;


    @Query("SELECT s FROM Student s WHERE s.firstName= :firstName and s.lastName = :lastName" )
    Optional<Student> findStudentByFirstNameAndLastName(
            @Param("firstName") String fistName,@Param("lastName") String lastName);



    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.id = ?1")
    int deleteStudentById(Long id);
}
