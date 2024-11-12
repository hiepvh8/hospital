package com.example.hospital.Model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorResponse {
    Long id;
    String position;
    String phone ;
    String email ;
    String fullname ;
    Date date_of_birth ;
    String degree;
    Integer numberOfReviews;
    Long totalScore;
    String images ;
}
