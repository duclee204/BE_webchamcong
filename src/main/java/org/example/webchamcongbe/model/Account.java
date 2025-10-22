package org.example.webchamcongbe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "account", indexes = {
        @Index(name = "role_id", columnList = "role_id"),
        @Index(name = "employee_id", columnList = "employee_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "username", columnNames = {"username"})
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private org.example.webchamcongbe.model.Role role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id", nullable = false)
    private org.example.webchamcongbe.model.Employee employee;

    @Lob
    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private Instant createdAt;

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

    public org.example.webchamcongbe.model.Role getRole() {
        return role;
    }

    public void setRole(org.example.webchamcongbe.model.Role role) {
        this.role = role;
    }

    public org.example.webchamcongbe.model.Employee getEmployee() {
        return employee;
    }

    public void setEmployee(org.example.webchamcongbe.model.Employee employee) {
        this.employee = employee;
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