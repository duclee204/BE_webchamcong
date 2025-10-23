package org.example.webchamcongbe.dto;

import java.util.Map;

public class RoleDTO {
    private Long id;
    private String roleName;
    private String description;
    private Map<String, Object> permissionDetails;

    public RoleDTO() {}

    public RoleDTO(Long id, String roleName, String description, Map<String, Object> permissionDetails) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.permissionDetails = permissionDetails;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Map<String, Object> getPermissionDetails() { return permissionDetails; }
    public void setPermissionDetails(Map<String, Object> permissionDetails) { this.permissionDetails = permissionDetails; }
}
