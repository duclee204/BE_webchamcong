package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.RoleDTO;
import org.example.webchamcongbe.model.Role;
import org.example.webchamcongbe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RoleDTO getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(this::convertToDTO).orElse(null);
    }

    public RoleDTO createRole(RoleDTO roleDTO) {
        if (roleRepository.existsByRoleName(roleDTO.getRoleName())) {
            throw new RuntimeException("Role name already exists");
        }
        Role role = convertToEntity(roleDTO);
        return convertToDTO(roleRepository.save(role));
    }

    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        existing.setRoleName(roleDTO.getRoleName());
        existing.setDescription(roleDTO.getDescription());
        existing.setPermissionDetails(roleDTO.getPermissionDetails());

        return convertToDTO(roleRepository.save(existing));
    }

    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found");
        }
        roleRepository.deleteById(id);
    }

    private RoleDTO convertToDTO(Role role) {
        return new RoleDTO(
                role.getId(),
                role.getRoleName(),
                role.getDescription(),
                role.getPermissionDetails()
        );
    }

    private Role convertToEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setRoleName(dto.getRoleName());
        role.setDescription(dto.getDescription());
        role.setPermissionDetails(dto.getPermissionDetails());
        return role;
    }
}
