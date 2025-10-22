package org.example.webchamcongbe.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "camera", uniqueConstraints = {
        @UniqueConstraint(name = "camera_code", columnNames = {"camera_code"})
})
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "camera_code", nullable = false, length = 50)
    private String cameraCode;

    @Column(name = "camera_name", nullable = false, length = 100)
    private String cameraName;

    @Column(name = "location", length = 200)
    private String location;

    @Column(name = "ip_address", length = 50)
    private String ipAddress;

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

    public String getCameraCode() {
        return cameraCode;
    }

    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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