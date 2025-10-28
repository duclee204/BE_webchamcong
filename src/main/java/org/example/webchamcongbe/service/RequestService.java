package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.RequestDTO;
import org.example.webchamcongbe.model.Employee;
import org.example.webchamcongbe.model.Request;
import org.example.webchamcongbe.repository.EmployeeRepository;
import org.example.webchamcongbe.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    // =============================
    // 1. Lấy danh sách request
    // =============================
    public List<RequestDTO> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // =============================
    // 2. Nhân viên tạo request
    // =============================
    public RequestDTO createRequest(RequestDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Request request = new Request();
        request.setEmployee(employee);
        request.setRequestType(dto.getRequestType());
        request.setStartTime(dto.getStartTime());
        request.setEndTime(dto.getEndTime());
        request.setReason(dto.getReason());
        request.setStatus("PENDING"); // mặc định chờ duyệt

        requestRepository.save(request);
        return convertToDTO(request);
    }

    // =============================
    // 3. Nhân viên chỉnh sửa request (chưa được duyệt / từ chối)
    // =============================
    public RequestDTO updateRequest(Long requestId, RequestDTO dto) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // Không cho sửa nếu đã duyệt hoặc từ chối
        if ("APPROVED".equalsIgnoreCase(request.getStatus()) || "REJECTED".equalsIgnoreCase(request.getStatus())) {
            throw new RuntimeException("Request đã được xử lý, không thể chỉnh sửa!");
        }

        // Chỉ cho phép nhân viên tạo request đó chỉnh sửa
        if (!request.getEmployee().getId().equals(dto.getEmployeeId())) {
            throw new RuntimeException("Bạn không có quyền chỉnh sửa request của người khác!");
        }

        // Cập nhật nội dung
        request.setRequestType(dto.getRequestType());
        request.setStartTime(dto.getStartTime());
        request.setEndTime(dto.getEndTime());
        request.setReason(dto.getReason());

        requestRepository.save(request);
        return convertToDTO(request);
    }

    // =============================
    // 4. Nhân viên xóa request (chưa duyệt / chưa từ chối)
    // =============================
    public void deleteRequest(Long requestId, Long employeeId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // Không cho xóa nếu đã duyệt hoặc từ chối
        if ("APPROVED".equalsIgnoreCase(request.getStatus()) || "REJECTED".equalsIgnoreCase(request.getStatus())) {
            throw new RuntimeException("Request đã được xử lý, không thể xóa!");
        }

        // Chỉ cho phép nhân viên tạo request đó xóa
        if (!request.getEmployee().getId().equals(employeeId)) {
            throw new RuntimeException("Bạn không có quyền xóa request của người khác!");
        }

        requestRepository.delete(request);
    }

    // =============================
    // 5. Admin duyệt / từ chối
    // =============================
    public RequestDTO approveOrRejectRequest(Long requestId, Long approverId, boolean approved) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Employee approver = employeeRepository.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        request.setApprover(approver);
        request.setStatus(approved ? "APPROVED" : "REJECTED");
        request.setApprovedAt(Instant.now());

        requestRepository.save(request);
        return convertToDTO(request);
    }

    // =============================
    // 6. Convert sang DTO
    // =============================
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
