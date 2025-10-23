package org.example.webchamcongbe.controller;

import org.example.webchamcongbe.dto.AccountDTO;
import org.example.webchamcongbe.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO dto = accountService.getAccountById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public AccountDTO createAccount(@RequestBody AccountDTO dto) {
        return accountService.createAccount(dto);
    }

    @PutMapping("/{id}")
    public AccountDTO updateAccount(@PathVariable Long id, @RequestBody AccountDTO dto) {
        return accountService.updateAccount(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
