package com.example.hospital.Model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientResponse {
    Long id;
    String phone ;
    String email ;
    String fullname ;
    String address;
    Date date_of_birth ;
    List<String> listPatientIllness;

}
