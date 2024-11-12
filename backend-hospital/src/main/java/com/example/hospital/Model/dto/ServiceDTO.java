package com.example.hospital.Model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceDTO {
    Long id;
    String name ;
    String expense ;
    String content ;
    String image;
    Integer status;
    Long majorId;

}
