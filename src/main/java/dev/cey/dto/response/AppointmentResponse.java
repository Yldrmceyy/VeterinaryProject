package dev.cey.dto.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDateTime;
    private Long animalId;
    private Long doctorId;
}
