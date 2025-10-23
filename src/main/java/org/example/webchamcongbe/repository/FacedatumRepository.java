package org.example.webchamcongbe.repository;

import org.example.webchamcongbe.model.Facedatum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacedatumRepository extends JpaRepository<Facedatum, Long> {
    List<Facedatum> findByEmployee_Id(Long employeeId);
}
