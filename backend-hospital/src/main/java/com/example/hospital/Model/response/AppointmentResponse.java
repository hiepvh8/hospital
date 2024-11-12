package com.example.hospital.Model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponse {
    Long id;
    String note;
    String username;
    String phone;
    String email;
    String fullName;
    String dateOfWeek;
}
