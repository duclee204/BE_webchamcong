package org.example.webchamcongbe.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class EmployeeDTO {
    private Long id;
    private String employeeCode;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Long departmentId;
    private String position;
    private BigDecimal baseSalary;
    private Integer standardWorkDays;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;

    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String employeeCode, String fullName, String email,
                       String phoneNumber, Long departmentId, String position,
                       BigDecimal baseSalary, Integer standardWorkDays,
                       String status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
        this.position = position;
        this.baseSalary = baseSalary;
        this.standardWorkDays = standardWorkDays;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public BigDecimal getBaseSalary() { return baseSalary; }
    public void setBaseSalary(BigDecimal baseSalary) { this.baseSalary = baseSalary; }

    public Integer getStandardWorkDays() { return standardWorkDays; }
    public void setStandardWorkDays(Integer standardWorkDays) { this.standardWorkDays = standardWorkDays; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
