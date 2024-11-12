package com.example.hospital.Model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiseasesDTO {
    String name ;
    String content;
    Long id;
    String image;
    Long MajorId;
}
