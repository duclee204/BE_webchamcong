package org.example.webchamcongbe.controller;

import org.example.webchamcongbe.dto.RequestDTO;
import org.example.webchamcongbe.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    // Lấy tất cả request
    @GetMapping
    public ResponseEntity<List<RequestDTO>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    // Nhân viên tạo request
    @PostMapping
    public ResponseEntity<RequestDTO> createRequest(@RequestBody RequestDTO dto) {
        return ResponseEntity.ok(requestService.createRequest(dto));
    }

    // Nhân viên chỉnh sửa request (nếu chưa duyệt)
    @PutMapping("/{id}")
    public ResponseEntity<RequestDTO> updateRequest(@PathVariable Long id, @RequestBody RequestDTO dto) {
        return ResponseEntity.ok(requestService.updateRequest(id, dto));
    }

    // Nhân viên xóa request (nếu chưa duyệt)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id, @RequestParam Long employeeId) {
        requestService.deleteRequest(id, employeeId);
        return ResponseEntity.ok("Request đã được xóa thành công.");
    }

    // Admin duyệt hoặc từ chối request
    @PostMapping("/{id}/approve")
    public ResponseEntity<RequestDTO> approveRequest(
            @PathVariable Long id,
            @RequestParam Long approverId,
            @RequestParam boolean approved
    ) {
        return ResponseEntity.ok(requestService.approveOrRejectRequest(id, approverId, approved));
    }
}
