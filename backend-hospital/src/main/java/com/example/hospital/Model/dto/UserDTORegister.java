package com.example.hospital.Model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTORegister {
    String username ;
    String password ;
    Integer status ;
    String phone ;
    String email ;
    String fullname ;
    Date date_of_birth ;
    String address ;
}
