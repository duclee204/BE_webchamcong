package org.example.webchamcongbe.dto;

import java.time.Instant;

public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private Long roleId;
    private Long employeeId;
    private String status;
    private Instant createdAt;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String username, String password, Long roleId, Long employeeId, String status, Instant createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.employeeId = employeeId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
