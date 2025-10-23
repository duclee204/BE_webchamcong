package org.example.webchamcongbe.dto;

import java.time.Instant;

public class CameraDTO {
    private Long id;
    private String cameraCode;
    private String cameraName;
    private String location;
    private String ipAddress;
    private String status;
    private Instant createdAt;

    public CameraDTO() {}

    public CameraDTO(Long id, String cameraCode, String cameraName, String location, String ipAddress, String status, Instant createdAt) {
        this.id = id;
        this.cameraCode = cameraCode;
        this.cameraName = cameraName;
        this.location = location;
        this.ipAddress = ipAddress;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCameraCode() { return cameraCode; }
    public void setCameraCode(String cameraCode) { this.cameraCode = cameraCode; }

    public String getCameraName() { return cameraName; }
    public void setCameraName(String cameraName) { this.cameraName = cameraName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
