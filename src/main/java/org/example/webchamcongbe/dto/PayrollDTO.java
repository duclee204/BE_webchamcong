package org.example.webchamcongbe.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class PayrollDTO {
    private Long id;
    private Long employeeId;
    private int month;
    private int year;
    private BigDecimal baseSalary;
    private BigDecimal allowance;
    private BigDecimal overtimePay;
    private BigDecimal bonus;
    private BigDecimal penalty;
    private BigDecimal deductions;
    private BigDecimal tax;
    private BigDecimal totalSalary;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;

    // Getters & Setters
    // ------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public BigDecimal getBaseSalary() { return baseSalary; }
    public void setBaseSalary(BigDecimal baseSalary) { this.baseSalary = baseSalary; }

    public BigDecimal getAllowance() { return allowance; }
    public void setAllowance(BigDecimal allowance) { this.allowance = allowance; }

    public BigDecimal getOvertimePay() { return overtimePay; }
    public void setOvertimePay(BigDecimal overtimePay) { this.overtimePay = overtimePay; }

    public BigDecimal getBonus() { return bonus; }
    public void setBonus(BigDecimal bonus) { this.bonus = bonus; }

    public BigDecimal getPenalty() { return penalty; }
    public void setPenalty(BigDecimal penalty) { this.penalty = penalty; }

    public BigDecimal getDeductions() { return deductions; }
    public void setDeductions(BigDecimal deductions) { this.deductions = deductions; }

    public BigDecimal getTax() { return tax; }
    public void setTax(BigDecimal tax) { this.tax = tax; }

    public BigDecimal getTotalSalary() { return totalSalary; }
    public void setTotalSalary(BigDecimal totalSalary) { this.totalSalary = totalSalary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
