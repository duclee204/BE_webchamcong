package org.example.webchamcongbe.controller;

import org.example.webchamcongbe.dto.FacedatumDTO;
import org.example.webchamcongbe.service.FacedatumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facedata")
public class FacedatumController {

    @Autowired
    private FacedatumService facedatumService;

    @GetMapping
    public List<FacedatumDTO> getAll() {
        return facedatumService.getAllFacedata();
    }

    @GetMapping("/{id}")
    public FacedatumDTO getById(@PathVariable Long id) {
        return facedatumService.getById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<FacedatumDTO> getByEmployee(@PathVariable Long employeeId) {
        return facedatumService.getByEmployeeId(employeeId);
    }

    @PostMapping
    public FacedatumDTO create(@RequestBody FacedatumDTO dto) {
        return facedatumService.createFacedatum(dto);
    }

    @PutMapping("/{id}")
    public FacedatumDTO update(@PathVariable Long id, @RequestBody FacedatumDTO dto) {
        return facedatumService.updateFacedatum(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facedatumService.deleteFacedatum(id);
    }
}
