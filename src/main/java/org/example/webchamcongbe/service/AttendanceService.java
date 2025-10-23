package org.example.webchamcongbe.service;

import org.example.webchamcongbe.dto.AttendanceDTO;
import org.example.webchamcongbe.model.Attendance;
import org.example.webchamcongbe.model.Employee;
import org.example.webchamcongbe.model.Camera;
import org.example.webchamcongbe.repository.AttendanceRepository;
import org.example.webchamcongbe.repository.EmployeeRepository;
import org.example.webchamcongbe.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CameraRepository cameraRepository;

    public List<AttendanceDTO> getAllAttendances() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AttendanceDTO getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
    }

    public AttendanceDTO createAttendance(AttendanceDTO dto) {
        Attendance attendance = convertToEntity(dto);
        return convertToDTO(attendanceRepository.save(attendance));
    }

    public AttendanceDTO updateAttendance(Long id, AttendanceDTO dto) {
        Attendance existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        existing.setWorkDate(dto.getWorkDate());
        existing.setCheckIn(dto.getCheckIn());
        existing.setCheckOut(dto.getCheckOut());
        existing.setTotalHours(dto.getTotalHours());
        existing.setStatus(dto.getStatus());
        existing.setNotes(dto.getNotes());
        existing.setCreatedAt(dto.getCreatedAt());

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            existing.setEmployee(employee);
        }

        if (dto.getCameraId() != null) {
            Camera camera = cameraRepository.findById(dto.getCameraId())
                    .orElseThrow(() -> new RuntimeException("Camera not found"));
            existing.setCamera(camera);
        }

        return convertToDTO(attendanceRepository.save(existing));
    }

    public void deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance not found");
        }
        attendanceRepository.deleteById(id);
    }

    private AttendanceDTO convertToDTO(Attendance attendance) {
        return new AttendanceDTO(
                attendance.getId(),
                attendance.getEmployee() != null ? attendance.getEmployee().getId() : null,
                attendance.getCamera() != null ? attendance.getCamera().getId() : null,
                attendance.getWorkDate(),
                attendance.getCheckIn(),
                attendance.getCheckOut(),
                attendance.getTotalHours(),
                attendance.getStatus(),
                attendance.getNotes(),
                attendance.getCreatedAt()
        );
    }

    private Attendance convertToEntity(AttendanceDTO dto) {
        Attendance attendance = new Attendance();
        attendance.setId(dto.getId());
        attendance.setWorkDate(dto.getWorkDate());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());
        attendance.setTotalHours(dto.getTotalHours());
        attendance.setStatus(dto.getStatus());
        attendance.setNotes(dto.getNotes());
        attendance.setCreatedAt(dto.getCreatedAt());

        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            attendance.setEmployee(employee);
        }

        if (dto.getCameraId() != null) {
            Camera camera = cameraRepository.findById(dto.getCameraId())
                    .orElseThrow(() -> new RuntimeException("Camera not found"));
            attendance.setCamera(camera);
        }

        return attendance;
    }
}
