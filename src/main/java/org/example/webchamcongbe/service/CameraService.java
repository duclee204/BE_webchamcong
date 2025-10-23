package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.CameraDTO;
import org.example.webchamcongbe.model.Camera;
import org.example.webchamcongbe.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    public List<CameraDTO> getAllCameras() {
        return cameraRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CameraDTO getCameraById(Long id) {
        Camera camera = cameraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Camera not found"));
        return convertToDTO(camera);
    }

    public CameraDTO createCamera(CameraDTO dto) {
        Camera camera = convertToEntity(dto);
        return convertToDTO(cameraRepository.save(camera));
    }

    public CameraDTO updateCamera(Long id, CameraDTO dto) {
        Camera existing = cameraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Camera not found"));

        existing.setCameraCode(dto.getCameraCode());
        existing.setCameraName(dto.getCameraName());
        existing.setLocation(dto.getLocation());
        existing.setIpAddress(dto.getIpAddress());
        existing.setStatus(dto.getStatus());
        existing.setCreatedAt(dto.getCreatedAt());

        return convertToDTO(cameraRepository.save(existing));
    }

    public void deleteCamera(Long id) {
        if (!cameraRepository.existsById(id)) {
            throw new RuntimeException("Camera not found");
        }
        cameraRepository.deleteById(id);
    }

    private CameraDTO convertToDTO(Camera camera) {
        return new CameraDTO(
                camera.getId(),
                camera.getCameraCode(),
                camera.getCameraName(),
                camera.getLocation(),
                camera.getIpAddress(),
                camera.getStatus(),
                camera.getCreatedAt()
        );
    }

    private Camera convertToEntity(CameraDTO dto) {
        Camera camera = new Camera();
        camera.setId(dto.getId());
        camera.setCameraCode(dto.getCameraCode());
        camera.setCameraName(dto.getCameraName());
        camera.setLocation(dto.getLocation());
        camera.setIpAddress(dto.getIpAddress());
        camera.setStatus(dto.getStatus());
        camera.setCreatedAt(dto.getCreatedAt());
        return camera;
    }
}
