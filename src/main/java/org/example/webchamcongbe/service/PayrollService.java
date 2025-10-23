package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.PayrollDTO;
import org.example.webchamcongbe.model.Employee;
import org.example.webchamcongbe.model.Payroll;
import org.example.webchamcongbe.repository.EmployeeRepository;
import org.example.webchamcongbe.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<PayrollDTO> getAllPayrolls() {
        return payrollRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PayrollDTO getById(Long id) {
        Optional<Payroll> payroll = payrollRepository.findById(id);
        return payroll.map(this::convertToDTO).orElse(null);
    }

    public List<PayrollDTO> getByEmployee(Long employeeId) {
        return payrollRepository.findByEmployee_Id(employeeId)
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PayrollDTO createPayroll(PayrollDTO dto) {
        if (payrollRepository.existsByEmployee_IdAndMonthAndYear(dto.getEmployeeId(), dto.getMonth(), dto.getYear())) {
            throw new RuntimeException("Payroll record already exists for this employee and month.");
        }

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Payroll payroll = convertToEntity(dto);
        payroll.setEmployee(employee);
        return convertToDTO(payrollRepository.save(payroll));
    }

    public PayrollDTO updatePayroll(Long id, PayrollDTO dto) {
        Payroll existing = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        if (dto.getTotalWorkHours() != null) existing.setTotalWorkHours(dto.getTotalWorkHours());
        if (dto.getTotalWorkDays() != null) existing.setTotalWorkDays(dto.getTotalWorkDays());
        if (dto.getBaseSalary() != null) existing.setBaseSalary(dto.getBaseSalary());
        if (dto.getAllowance() != null) existing.setAllowance(dto.getAllowance());
        if (dto.getOvertimePay() != null) existing.setOvertimePay(dto.getOvertimePay());
        if (dto.getDeductions() != null) existing.setDeductions(dto.getDeductions());
        if (dto.getTax() != null) existing.setTax(dto.getTax());
        if (dto.getBonus() != null) existing.setBonus(dto.getBonus());
        if (dto.getPenalty() != null) existing.setPenalty(dto.getPenalty());
        if (dto.getTotalSalary() != null) existing.setTotalSalary(dto.getTotalSalary());
        if (dto.getStatus() != null) existing.setStatus(dto.getStatus());
        if (dto.getUpdatedAt() != null) existing.setUpdatedAt(dto.getUpdatedAt());

        return convertToDTO(payrollRepository.save(existing));
    }

    public void deletePayroll(Long id) {
        if (!payrollRepository.existsById(id)) {
            throw new RuntimeException("Payroll not found");
        }
        payrollRepository.deleteById(id);
    }

    // --- Convert ---
    private PayrollDTO convertToDTO(Payroll p) {
        return new PayrollDTO(
                p.getId(),
                p.getEmployee() != null ? p.getEmployee().getId() : null,
                p.getMonth(),
                p.getYear(),
                p.getTotalWorkHours(),
                p.getTotalWorkDays(),
                p.getBaseSalary(),
                p.getAllowance(),
                p.getOvertimePay(),
                p.getDeductions(),
                p.getTax(),
                p.getBonus(),
                p.getPenalty(),
                p.getTotalSalary(),
                p.getStatus(),
                p.getCreatedAt(),
                p.getUpdatedAt()
        );
    }

    private Payroll convertToEntity(PayrollDTO dto) {
        Payroll p = new Payroll();
        p.setId(dto.getId());
        p.setMonth(dto.getMonth());
        p.setYear(dto.getYear());
        p.setTotalWorkHours(dto.getTotalWorkHours());
        p.setTotalWorkDays(dto.getTotalWorkDays());
        p.setBaseSalary(dto.getBaseSalary());
        p.setAllowance(dto.getAllowance());
        p.setOvertimePay(dto.getOvertimePay());
        p.setDeductions(dto.getDeductions());
        p.setTax(dto.getTax());
        p.setBonus(dto.getBonus());
        p.setPenalty(dto.getPenalty());
        p.setTotalSalary(dto.getTotalSalary());
        p.setStatus(dto.getStatus());
        p.setCreatedAt(dto.getCreatedAt());
        p.setUpdatedAt(dto.getUpdatedAt());
        return p;
    }
}
