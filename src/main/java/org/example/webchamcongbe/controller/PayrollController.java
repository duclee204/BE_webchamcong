package org.example.webchamcongbe.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.webchamcongbe.dto.PayrollDTO;
import org.example.webchamcongbe.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payrolls")
@CrossOrigin(origins = "*")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    // Lấy toàn bộ danh sách bảng lương
    @GetMapping
    public ResponseEntity<?> getAllPayrolls() {
        return ResponseEntity.ok(payrollService.getAllPayrolls());
    }

    // Tính lương cho 1 nhân viên
    @PostMapping("/calculate/{employeeId}")
    public ResponseEntity<PayrollDTO> calculatePayroll(
            @PathVariable Long employeeId,
            @RequestParam int month,
            @RequestParam int year,
            @RequestParam double baseSalary) {

        PayrollDTO result = payrollService.calculatePayroll(employeeId, month, year, BigDecimal.valueOf(baseSalary));
        return ResponseEntity.ok(result);
    }

    // ==========================
    // EXPORT CSV (Excel có thể mở được)
    // ==========================
    @GetMapping("/export/csv")
    public void exportCSV(
            @RequestParam int month,
            @RequestParam int year,
            HttpServletResponse response) throws IOException {

        payrollService.exportPayrollToCSV(response, month, year);
    }

    // ==========================
    // EXPORT HTML (để in PDF)
    // ==========================
    @GetMapping("/export/PDF")
    public ResponseEntity<String> exportHTML(
            @RequestParam int month,
            @RequestParam int year) {

        String html = payrollService.generatePayrollHTML(month, year);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }
}
