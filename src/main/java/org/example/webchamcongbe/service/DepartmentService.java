package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.DepartmentDTO;
import org.example.webchamcongbe.model.Department;
import org.example.webchamcongbe.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Lấy tất cả phòng ban
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Lấy phòng ban theo id
    public DepartmentDTO getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(this::convertToDTO).orElse(null);
    }

    // Tạo mới phòng ban
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        if (departmentRepository.existsByDepartmentName(dto.getDepartmentName())) {
            throw new RuntimeException("Department name already exists");
        }
        Department department = convertToEntity(dto);
        return convertToDTO(departmentRepository.save(department));
    }

    // Cập nhật phòng ban
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existing.setDepartmentName(dto.getDepartmentName());
        existing.setDescription(dto.getDescription());

        return convertToDTO(departmentRepository.save(existing));
    }

    // Xóa phòng ban
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found");
        }
        departmentRepository.deleteById(id);
    }

    // Chuyển đổi Entity → DTO
    private DepartmentDTO convertToDTO(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDescription()
        );
    }

    // Chuyển đổi DTO → Entity
    private Department convertToEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setDepartmentName(dto.getDepartmentName());
        department.setDescription(dto.getDescription());
        return department;
    }
}
