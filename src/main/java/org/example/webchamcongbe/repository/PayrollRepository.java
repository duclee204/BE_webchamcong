package org.example.webchamcongbe.repository;

import org.example.webchamcongbe.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByEmployee_Id(Long employeeId);
    Optional<Payroll> findByEmployee_IdAndMonthAndYear(Long employeeId, Integer month, Integer year);
    boolean existsByEmployee_IdAndMonthAndYear(Long employeeId, Integer month, Integer year);
}
