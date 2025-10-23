package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.AccountDTO;
import org.example.webchamcongbe.model.Account;
import org.example.webchamcongbe.model.Employee;
import org.example.webchamcongbe.model.Role;
import org.example.webchamcongbe.repository.AccountRepository;
import org.example.webchamcongbe.repository.EmployeeRepository;
import org.example.webchamcongbe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Lấy tất cả tài khoản
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Lấy tài khoản theo id
    public AccountDTO getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(this::convertToDTO).orElse(null);
    }

    // Tạo tài khoản
    public AccountDTO createAccount(AccountDTO dto) {
        if (accountRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Account account = new Account();
        account.setUsername(dto.getUsername());
        account.setPassword(dto.getPassword());
        account.setRole(role);
        account.setEmployee(employee);
        account.setStatus(dto.getStatus());
        account.setCreatedAt(Instant.now());

        return convertToDTO(accountRepository.save(account));
    }

    // Cập nhật tài khoản
    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        Account existing = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        existing.setUsername(dto.getUsername());
        existing.setPassword(dto.getPassword());
        existing.setStatus(dto.getStatus());

        if (dto.getRoleId() != null) {
            Role role = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            existing.setRole(role);
        }

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            existing.setEmployee(employee);
        }

        return convertToDTO(accountRepository.save(existing));
    }

    // Xóa tài khoản
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found");
        }
        accountRepository.deleteById(id);
    }

    // Convert Entity -> DTO
    private AccountDTO convertToDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getUsername(),
                account.getPassword(),
                account.getRole() != null ? account.getRole().getId() : null,
                account.getEmployee() != null ? account.getEmployee().getId() : null,
                account.getStatus(),
                account.getCreatedAt()
        );
    }
}
