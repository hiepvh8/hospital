package com.example.hospital.Model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientRequest {

    String phone ;
    String email ;
    String fullname ;
    String patientIllness;
}
