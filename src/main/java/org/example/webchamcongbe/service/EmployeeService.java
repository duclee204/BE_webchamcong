package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.EmployeeDTO;
import org.example.webchamcongbe.model.Department;
import org.example.webchamcongbe.model.Employee;
import org.example.webchamcongbe.repository.DepartmentRepository;
import org.example.webchamcongbe.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(this::convertToDTO).orElse(null);
    }

    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        if (employeeRepository.existsByEmployeeCode(dto.getEmployeeCode())) {
            throw new RuntimeException("Employee code already exists");
        }
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Employee employee = convertToEntity(dto);
        employee.setCreatedAt(Instant.now());
        employee.setUpdatedAt(Instant.now());
        return convertToDTO(employeeRepository.save(employee));
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setEmployeeCode(dto.getEmployeeCode());
        existing.setFullName(dto.getFullName());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setPosition(dto.getPosition());
        existing.setBaseSalary(dto.getBaseSalary());
        existing.setStandardWorkDays(dto.getStandardWorkDays());
        existing.setStatus(dto.getStatus());
        existing.setUpdatedAt(Instant.now());

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            existing.setDepartment(department);
        }

        return convertToDTO(employeeRepository.save(existing));
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getFullName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getDepartment() != null ? employee.getDepartment().getId() : null,
                employee.getPosition(),
                employee.getBaseSalary(),
                employee.getStandardWorkDays(),
                employee.getStatus(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

    private Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setEmployeeCode(dto.getEmployeeCode());
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setPosition(dto.getPosition());
        employee.setBaseSalary(dto.getBaseSalary());
        employee.setStandardWorkDays(dto.getStandardWorkDays());
        employee.setStatus(dto.getStatus());

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            employee.setDepartment(department);
        }

        return employee;
    }
}
