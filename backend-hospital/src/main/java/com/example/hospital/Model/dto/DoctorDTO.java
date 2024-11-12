package com.example.hospital.Model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorDTO {
    Long id;
    String username ;
    String password ;
    String phone ;
    String email ;
    String fullname ;
    Date date_of_birth ;
    String roles ;
    String degree;
    String image;
    String position ;
    Long majorId;

}
