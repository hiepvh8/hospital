package com.example.hospital.Model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceResponse {
    Long id;
    String name ;
    String expense ;
    String content ;
    String image;
    Integer status;
}
