package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.RequestDTO;
import org.example.webchamcongbe.model.Employee;
import org.example.webchamcongbe.model.Request;
import org.example.webchamcongbe.repository.EmployeeRepository;
import org.example.webchamcongbe.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final EmployeeRepository employeeRepository;

    public RequestService(RequestRepository requestRepository, EmployeeRepository employeeRepository) {
        this.requestRepository = requestRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<RequestDTO> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RequestDTO createRequest(RequestDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Employee approver = null;
        if (dto.getApproverId() != null) {
            approver = employeeRepository.findById(dto.getApproverId())
                    .orElse(null);
        }

        Request request = new Request();
        request.setEmployee(employee);
        request.setApprover(approver);
        request.setRequestType(dto.getRequestType());
        request.setStartTime(dto.getStartTime());
        request.setEndTime(dto.getEndTime());
        request.setReason(dto.getReason());
        request.setStatus(dto.getStatus());
        request.setApprovedAt(dto.getApprovedAt());

        requestRepository.save(request);
        return convertToDTO(request);
    }

    private RequestDTO convertToDTO(Request request) {
        RequestDTO dto = new RequestDTO();
        dto.setId(request.getId());
        dto.setRequestType(request.getRequestType());
        dto.setStartTime(request.getStartTime());
        dto.setEndTime(request.getEndTime());
        dto.setReason(request.getReason());
        dto.setStatus(request.getStatus());
        dto.setApprovedAt(request.getApprovedAt());

        if (request.getEmployee() != null) {
            dto.setEmployeeId(request.getEmployee().getId());
            dto.setEmployeeName(request.getEmployee().getFullName());
        }

        if (request.getApprover() != null) {
            dto.setApproverId(request.getApprover().getId());
            dto.setApproverName(request.getApprover().getFullName());
        }

        return dto;
    }
}
