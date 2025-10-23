package org.example.webchamcongbe.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class FacedatumDTO {
    private Long id;
    private Long employeeId;
    private String imagePath;
    private byte[] embeddingData;
    private BigDecimal recognitionThreshold;
    private Instant createdAt;

    public FacedatumDTO() {}

    public FacedatumDTO(Long id, Long employeeId, String imagePath, byte[] embeddingData, BigDecimal recognitionThreshold, Instant createdAt) {
        this.id = id;
        this.employeeId = employeeId;
        this.imagePath = imagePath;
        this.embeddingData = embeddingData;
        this.recognitionThreshold = recognitionThreshold;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public byte[] getEmbeddingData() {
        return embeddingData;
    }

    public void setEmbeddingData(byte[] embeddingData) {
        this.embeddingData = embeddingData;
    }

    public BigDecimal getRecognitionThreshold() {
        return recognitionThreshold;
    }

    public void setRecognitionThreshold(BigDecimal recognitionThreshold) {
        this.recognitionThreshold = recognitionThreshold;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
