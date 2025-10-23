package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.FacedatumDTO;
import org.example.webchamcongbe.model.Employee;
import org.example.webchamcongbe.model.Facedatum;
import org.example.webchamcongbe.repository.EmployeeRepository;
import org.example.webchamcongbe.repository.FacedatumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacedatumService {

    @Autowired
    private FacedatumRepository facedatumRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<FacedatumDTO> getAllFacedata() {
        return facedatumRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FacedatumDTO> getByEmployeeId(Long employeeId) {
        return facedatumRepository.findByEmployee_Id(employeeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FacedatumDTO getById(Long id) {
        Optional<Facedatum> facedatum = facedatumRepository.findById(id);
        return facedatum.map(this::convertToDTO).orElse(null);
    }

    public FacedatumDTO createFacedatum(FacedatumDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Facedatum facedatum = convertToEntity(dto);
        facedatum.setEmployee(employee);

        return convertToDTO(facedatumRepository.save(facedatum));
    }

    public FacedatumDTO updateFacedatum(Long id, FacedatumDTO dto) {
        Facedatum existing = facedatumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facedatum not found"));

        if (dto.getImagePath() != null) existing.setImagePath(dto.getImagePath());
        if (dto.getEmbeddingData() != null) existing.setEmbeddingData(dto.getEmbeddingData());
        if (dto.getRecognitionThreshold() != null) existing.setRecognitionThreshold(dto.getRecognitionThreshold());
        if (dto.getCreatedAt() != null) existing.setCreatedAt(dto.getCreatedAt());

        return convertToDTO(facedatumRepository.save(existing));
    }

    public void deleteFacedatum(Long id) {
        if (!facedatumRepository.existsById(id)) {
            throw new RuntimeException("Facedatum not found");
        }
        facedatumRepository.deleteById(id);
    }

    private FacedatumDTO convertToDTO(Facedatum f) {
        return new FacedatumDTO(
                f.getId(),
                f.getEmployee() != null ? f.getEmployee().getId() : null,
                f.getImagePath(),
                f.getEmbeddingData(),
                f.getRecognitionThreshold(),
                f.getCreatedAt()
        );
    }

    private Facedatum convertToEntity(FacedatumDTO dto) {
        Facedatum f = new Facedatum();
        f.setId(dto.getId());
        f.setImagePath(dto.getImagePath());
        f.setEmbeddingData(dto.getEmbeddingData());
        f.setRecognitionThreshold(dto.getRecognitionThreshold());
        f.setCreatedAt(dto.getCreatedAt());
        return f;
    }
}
