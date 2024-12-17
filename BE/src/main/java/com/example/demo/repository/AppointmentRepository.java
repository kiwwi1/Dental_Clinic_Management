package com.example.demo.repository;

import com.example.demo.dto.DentistDto;
import com.example.demo.model.Appointment;
import com.example.demo.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.springframework.http.HttpHeaders.FROM;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("""
        SELECT d
        FROM Dentist d
        JOIN Treatment t ON d.id = t.dentist.id
        JOIN Patient p ON t.patient.id = p.id
        JOIN Appointment a ON a.patient.id = p.id
        WHERE a.id = :appointmentId
    """)
    Optional<Dentist> findAppointmentWithDentist(@Param("appointmentId") int appointmentId);

    @Query("SELECT a FROM Appointment a WHERE a.id = :id")
    Appointment findById(@Param("id") int id);

}

