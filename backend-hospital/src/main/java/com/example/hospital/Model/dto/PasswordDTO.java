package com.example.hospital.Model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordDTO {
    String password;
    String newPassword;
    String confirmPassword;
}
