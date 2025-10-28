package org.example.webchamcongbe.repository;

import org.example.webchamcongbe.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployee_Department_Id(Long departmentId);
}
