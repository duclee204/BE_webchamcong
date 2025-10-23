package org.example.webchamcongbe.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class PayrollDTO {
    private Long id;
    private Long employeeId;
    private Integer month;
    private Integer year;
    private BigDecimal totalWorkHours;
    private Integer totalWorkDays;
    private BigDecimal baseSalary;
    private BigDecimal allowance;
    private BigDecimal overtimePay;
    private BigDecimal deductions;
    private BigDecimal tax;
    private BigDecimal bonus;
    private BigDecimal penalty;
    private BigDecimal totalSalary;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;

    public PayrollDTO() {}

    public PayrollDTO(Long id, Long employeeId, Integer month, Integer year,
                      BigDecimal totalWorkHours, Integer totalWorkDays,
                      BigDecimal baseSalary, BigDecimal allowance,
                      BigDecimal overtimePay, BigDecimal deductions,
                      BigDecimal tax, BigDecimal bonus, BigDecimal penalty,
                      BigDecimal totalSalary, String status,
                      Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
        this.totalWorkHours = totalWorkHours;
        this.totalWorkDays = totalWorkDays;
        this.baseSalary = baseSalary;
        this.allowance = allowance;
        this.overtimePay = overtimePay;
        this.deductions = deductions;
        this.tax = tax;
        this.bonus = bonus;
        this.penalty = penalty;
        this.totalSalary = totalSalary;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public BigDecimal getTotalWorkHours() { return totalWorkHours; }
    public void setTotalWorkHours(BigDecimal totalWorkHours) { this.totalWorkHours = totalWorkHours; }

    public Integer getTotalWorkDays() { return totalWorkDays; }
    public void setTotalWorkDays(Integer totalWorkDays) { this.totalWorkDays = totalWorkDays; }

    public BigDecimal getBaseSalary() { return baseSalary; }
    public void setBaseSalary(BigDecimal baseSalary) { this.baseSalary = baseSalary; }

    public BigDecimal getAllowance() { return allowance; }
    public void setAllowance(BigDecimal allowance) { this.allowance = allowance; }

    public BigDecimal getOvertimePay() { return overtimePay; }
    public void setOvertimePay(BigDecimal overtimePay) { this.overtimePay = overtimePay; }

    public BigDecimal getDeductions() { return deductions; }
    public void setDeductions(BigDecimal deductions) { this.deductions = deductions; }

    public BigDecimal getTax() { return tax; }
    public void setTax(BigDecimal tax) { this.tax = tax; }

    public BigDecimal getBonus() { return bonus; }
    public void setBonus(BigDecimal bonus) { this.bonus = bonus; }

    public BigDecimal getPenalty() { return penalty; }
    public void setPenalty(BigDecimal penalty) { this.penalty = penalty; }

    public BigDecimal getTotalSalary() { return totalSalary; }
    public void setTotalSalary(BigDecimal totalSalary) { this.totalSalary = totalSalary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
