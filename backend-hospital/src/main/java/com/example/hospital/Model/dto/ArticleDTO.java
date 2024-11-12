package com.example.hospital.Model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleDTO {
    Long id;
    String title ;
    String content;
    String image;
    Long majorID;
}
