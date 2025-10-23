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

    @GetMapping
    public ResponseEntity<List<RequestDTO>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @PostMapping
    public ResponseEntity<RequestDTO> createRequest(@RequestBody RequestDTO dto) {
        return ResponseEntity.ok(requestService.createRequest(dto));
    }
}
