package org.example.webchamcongbe.repository;

import org.example.webchamcongbe.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    boolean existsByEmployeeIdAndMonthAndYear(Long employeeId, int month, int year);
    List<Payroll> findByMonthAndYear(int month, int year);
}
