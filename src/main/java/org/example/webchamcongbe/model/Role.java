package org.example.webchamcongbe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(name = "role_name", columnNames = {"role_name"})
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "permission_details")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> permissionDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getPermissionDetails() {
        return permissionDetails;
    }

    public void setPermissionDetails(Map<String, Object> permissionDetails) {
        this.permissionDetails = permissionDetails;
    }

}