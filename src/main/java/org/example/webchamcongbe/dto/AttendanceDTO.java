package org.example.webchamcongbe.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public class AttendanceDTO {
    private Long id;
    private Long employeeId;
    private Long cameraId;
    private LocalDate workDate;
    private Instant checkIn;
    private Instant checkOut;
    private BigDecimal totalHours;
    private String status;
    private String notes;
    private Instant createdAt;

    public AttendanceDTO() {
    }

    public AttendanceDTO(Long id, Long employeeId, Long cameraId, LocalDate workDate, Instant checkIn, Instant checkOut,
                         BigDecimal totalHours, String status, String notes, Instant createdAt) {
        this.id = id;
        this.employeeId = employeeId;
        this.cameraId = cameraId;
        this.workDate = workDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalHours = totalHours;
        this.status = status;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public Long getCameraId() { return cameraId; }
    public void setCameraId(Long cameraId) { this.cameraId = cameraId; }
    public LocalDate getWorkDate() { return workDate; }
    public void setWorkDate(LocalDate workDate) { this.workDate = workDate; }
    public Instant getCheckIn() { return checkIn; }
    public void setCheckIn(Instant checkIn) { this.checkIn = checkIn; }
    public Instant getCheckOut() { return checkOut; }
    public void setCheckOut(Instant checkOut) { this.checkOut = checkOut; }
    public BigDecimal getTotalHours() { return totalHours; }
    public void setTotalHours(BigDecimal totalHours) { this.totalHours = totalHours; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
