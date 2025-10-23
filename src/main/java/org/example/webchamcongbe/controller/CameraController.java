package org.example.webchamcongbe.controller;

import org.example.webchamcongbe.dto.CameraDTO;
import org.example.webchamcongbe.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cameras")
@CrossOrigin(origins = "*")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @GetMapping
    public List<CameraDTO> getAllCameras() {
        return cameraService.getAllCameras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CameraDTO> getCameraById(@PathVariable Long id) {
        return ResponseEntity.ok(cameraService.getCameraById(id));
    }

    @PostMapping
    public ResponseEntity<CameraDTO> createCamera(@RequestBody CameraDTO dto) {
        return ResponseEntity.ok(cameraService.createCamera(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CameraDTO> updateCamera(@PathVariable Long id, @RequestBody CameraDTO dto) {
        return ResponseEntity.ok(cameraService.updateCamera(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamera(@PathVariable Long id) {
        cameraService.deleteCamera(id);
        return ResponseEntity.noContent().build();
    }
}
