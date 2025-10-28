package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.PayrollDTO;
import org.example.webchamcongbe.model.Payroll;
import org.example.webchamcongbe.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    // Convert Entity <-> DTO
    private PayrollDTO convertToDTO(Payroll payroll) {
        PayrollDTO dto = new PayrollDTO();
        dto.setId(payroll.getId());
        dto.setEmployeeId(payroll.getEmployeeId());
        dto.setMonth(payroll.getMonth());
        dto.setYear(payroll.getYear());
        dto.setBaseSalary(payroll.getBaseSalary());
        dto.setAllowance(payroll.getAllowance());
        dto.setOvertimePay(payroll.getOvertimePay());
        dto.setBonus(payroll.getBonus());
        dto.setPenalty(payroll.getPenalty());
        dto.setDeductions(payroll.getDeductions());
        dto.setTax(payroll.getTax());
        dto.setTotalSalary(payroll.getTotalSalary());
        dto.setStatus(payroll.getStatus());
        dto.setCreatedAt(payroll.getCreatedAt());
        dto.setUpdatedAt(payroll.getUpdatedAt());
        return dto;
    }

    // Lấy tất cả bảng lương
    public List<PayrollDTO> getAllPayrolls() {
        return payrollRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Tính lương
    public PayrollDTO calculatePayroll(Long employeeId, int month, int year, BigDecimal baseSalary) {
        if (payrollRepository.existsByEmployeeIdAndMonthAndYear(employeeId, month, year)) {
            throw new RuntimeException("Payroll already exists for this employee in this period");
        }

        BigDecimal allowance = new BigDecimal("1000000");
        BigDecimal overtimePay = new BigDecimal("500000");
        BigDecimal bonus = new BigDecimal("300000");
        BigDecimal penalty = new BigDecimal("100000");
        BigDecimal deductions = new BigDecimal("500000");
        BigDecimal tax = baseSalary.multiply(new BigDecimal("0.05"));
        BigDecimal totalSalary = baseSalary
                .add(allowance)
                .add(overtimePay)
                .add(bonus)
                .subtract(penalty)
                .subtract(deductions)
                .subtract(tax);

        Payroll payroll = new Payroll();
        payroll.setEmployeeId(employeeId);
        payroll.setMonth(month);
        payroll.setYear(year);
        payroll.setBaseSalary(baseSalary);
        payroll.setAllowance(allowance);
        payroll.setOvertimePay(overtimePay);
        payroll.setBonus(bonus);
        payroll.setPenalty(penalty);
        payroll.setDeductions(deductions);
        payroll.setTax(tax);
        payroll.setTotalSalary(totalSalary);
        payroll.setStatus("COMPLETED");
        payroll.setCreatedAt(Instant.now());
        payroll.setUpdatedAt(Instant.now());

        return convertToDTO(payrollRepository.save(payroll));
    }

    // -------------------------------
    // XUẤT FILE CSV (Excel có thể mở được)
    // -------------------------------
    public void exportPayrollToCSV(HttpServletResponse response, int month, int year) throws IOException {
        List<Payroll> payrolls = payrollRepository.findByMonthAndYear(month, year);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=payroll_" + month + "_" + year + ".csv");

        PrintWriter writer = response.getWriter();
        writer.println("ID,Employee ID,Base Salary,Total Salary,Status");

        for (Payroll p : payrolls) {
            writer.println(String.join(",",
                    String.valueOf(p.getId()),
                    String.valueOf(p.getEmployeeId()),
                    p.getBaseSalary().toString(),
                    p.getTotalSalary().toString(),
                    p.getStatus()
            ));
        }

        writer.flush();
        writer.close();
    }

    // -------------------------------
    // XUẤT FILE HTML -> In ra PDF (Frontend)
    // -------------------------------
    public String generatePayrollHTML(int month, int year) {
        List<Payroll> payrolls = payrollRepository.findByMonthAndYear(month, year);

        StringBuilder html = new StringBuilder();
        html.append("<html><head><meta charset='UTF-8'><title>Bảng lương tháng ")
                .append(month).append("/").append(year)
                .append("</title></head><body>");
        html.append("<h2 style='text-align:center;'>BẢNG LƯƠNG THÁNG ").append(month).append("/").append(year).append("</h2>");
        html.append("<table border='1' cellspacing='0' cellpadding='5' style='width:100%; border-collapse:collapse;'>");
        html.append("<tr><th>ID</th><th>Employee ID</th><th>Base Salary</th><th>Total Salary</th><th>Status</th></tr>");

        for (Payroll p : payrolls) {
            html.append("<tr>")
                    .append("<td>").append(p.getId()).append("</td>")
                    .append("<td>").append(p.getEmployeeId()).append("</td>")
                    .append("<td>").append(p.getBaseSalary()).append("</td>")
                    .append("<td>").append(p.getTotalSalary()).append("</td>")
                    .append("<td>").append(p.getStatus()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table>");
        html.append("<script>window.print();</script>");
        html.append("</body></html>");

        return html.toString();
    }
}
