package com.example.hospital.Service;

import com.example.hospital.Model.dto.PasswordDTO;
import com.example.hospital.Model.dto.UserDTO;
import com.example.hospital.Model.dto.UserDTOLogin;
import com.example.hospital.Model.dto.UserDTORegister;
import com.example.hospital.Model.request.PatientRequest;
import com.example.hospital.Model.response.PatientResponse;

import java.util.List;

public interface UserService {
    List<PatientResponse> findByRequest(PatientRequest request);
    void Register(UserDTORegister userDTO);
    String Login(UserDTOLogin userDTO );
    void ChangePassword(PasswordDTO passwordDTO);

}
