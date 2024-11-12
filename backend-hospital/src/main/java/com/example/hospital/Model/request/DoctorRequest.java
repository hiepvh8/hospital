package com.example.hospital.Model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRequest {
    String position;
    String phone ;
    String email ;
    String fullname ;
    Long major;
    String service;
}
