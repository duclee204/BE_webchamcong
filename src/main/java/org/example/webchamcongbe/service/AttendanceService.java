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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CameraRepository cameraRepository;

    /**
     * 📋 Lấy toàn bộ chấm công
     */
    public List<AttendanceDTO> getAllAttendances() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 🔍 Lấy chấm công theo ID
     */
    public AttendanceDTO getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
    }

    /**
     * ➕ Thêm mới chấm công (chỉ cho phép khi bảng chưa bị khóa)
     */
    @Transactional
    public AttendanceDTO createAttendance(AttendanceDTO dto) {
        Attendance attendance = convertToEntity(dto);

        // Kiểm tra nhân viên có bị khóa không
        if (attendance.getEmployee() != null) {
            List<Attendance> existingList = attendanceRepository.findByEmployee_Department_Id(
                    attendance.getEmployee().getDepartment().getId()
            );
            boolean isLocked = existingList.stream()
                    .anyMatch(a -> "Đã khóa".equalsIgnoreCase(a.getStatus()));
            if (isLocked) {
                throw new RuntimeException("Bảng chấm công phòng ban này đã bị khóa, không thể thêm mới!");
            }
        }

        return convertToDTO(attendanceRepository.save(attendance));
    }

    /**
     * ✏️ Cập nhật chấm công (chỉ cho phép khi chưa bị khóa)
     */
    @Transactional
    public AttendanceDTO updateAttendance(Long id, AttendanceDTO dto) {
        Attendance existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        if ("Đã khóa".equalsIgnoreCase(existing.getStatus())) {
            throw new RuntimeException("Bảng chấm công đã bị khóa, không thể chỉnh sửa!");
        }

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

    /**
     * ❌ Xóa chấm công (chỉ cho phép khi chưa bị khóa)
     */
    @Transactional
    public void deleteAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        if ("Đã khóa".equalsIgnoreCase(attendance.getStatus())) {
            throw new RuntimeException("Bảng chấm công đã bị khóa, không thể xóa!");
        }

        attendanceRepository.delete(attendance);
    }

    /**
     * 🔒 Khóa bảng chấm công theo phòng ban
     */
    @Transactional
    public void lockDepartment(Long departmentId) {
        List<Attendance> list = attendanceRepository.findByEmployee_Department_Id(departmentId);
        if (list.isEmpty()) {
            throw new RuntimeException("Không tìm thấy dữ liệu chấm công cho phòng ban này!");
        }

        list.forEach(a -> a.setStatus("Đã khóa"));
        attendanceRepository.saveAll(list);
    }

    /**
     * 🔓 Mở khóa bảng chấm công theo phòng ban
     */
    @Transactional
    public void unlockDepartment(Long departmentId) {
        List<Attendance> list = attendanceRepository.findByEmployee_Department_Id(departmentId);
        if (list.isEmpty()) {
            throw new RuntimeException("Không tìm thấy dữ liệu chấm công cho phòng ban này!");
        }

        list.forEach(a -> a.setStatus("Mở khóa"));
        attendanceRepository.saveAll(list);
    }

    // -------------------
    // Helper conversion
    // -------------------
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
