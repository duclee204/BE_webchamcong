package org.example.webchamcongbe.controller;

import org.example.webchamcongbe.dto.PayrollDTO;
import org.example.webchamcongbe.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payrolls")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping
    public List<PayrollDTO> getAll() {
        return payrollService.getAllPayrolls();
    }

    @GetMapping("/{id}")
    public PayrollDTO getById(@PathVariable Long id) {
        return payrollService.getById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<PayrollDTO> getByEmployee(@PathVariable Long employeeId) {
        return payrollService.getByEmployee(employeeId);
    }

    @PostMapping
    public PayrollDTO create(@RequestBody PayrollDTO dto) {
        return payrollService.createPayroll(dto);
    }

    @PutMapping("/{id}")
    public PayrollDTO update(@PathVariable Long id, @RequestBody PayrollDTO dto) {
        return payrollService.updatePayroll(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        payrollService.deletePayroll(id);
    }
}
