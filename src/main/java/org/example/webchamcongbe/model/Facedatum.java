package org.example.webchamcongbe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "facedata", indexes = {
        @Index(name = "employee_id", columnList = "employee_id")
})
public class Facedatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "embedding_data")
    private byte[] embeddingData;

    @Column(name = "recognition_threshold", precision = 4, scale = 2)
    private BigDecimal recognitionThreshold;

    @Column(name = "created_at")
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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