package com.example.hospital.Model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrowseAppoinmentRequest {
    Long doctorId;
    Date appointmentDate;
    Long appoinmentId;
    Integer status;
}
