package com.example.hospital.Service.impl;

import com.example.hospital.Converter.PatientConverter;
import com.example.hospital.Entity.PatientEntity;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Exception.CustomException.AccountExists;
import com.example.hospital.Exception.CustomException.DataNotFoundException;
import com.example.hospital.Exception.CustomException.DuplicatedUsername;
import com.example.hospital.Model.dto.PasswordDTO;
import com.example.hospital.Model.dto.UserDTO;
import com.example.hospital.Model.dto.UserDTOLogin;
import com.example.hospital.Model.dto.UserDTORegister;
import com.example.hospital.Model.request.PatientRequest;
import com.example.hospital.Model.response.PatientResponse;
import com.example.hospital.Repository.PatientRepository;
import com.example.hospital.Repository.UserRepository;
import com.example.hospital.Service.UserService;
import com.example.hospital.Utils.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientConverter patientConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public List<PatientResponse> findByRequest(PatientRequest request) {
        List<UserEntity> list = userRepository.findPatientByRequest(request);
        List<PatientResponse> patientResponses = new ArrayList<>();
        for (UserEntity user : list) {
            PatientResponse patientResponse = patientConverter.ConverterFromUserEntity(user);
            patientResponses.add(patientResponse);
        }
        return patientResponses;
    }

    @Override
    public void Register(UserDTORegister userDTO) {
        UserEntity users = modelMapper.map(userDTO, UserEntity.class);
        UserEntity userEntity = userRepository.findByUsername(userDTO.getUsername());
        if(userEntity != null) {
            throw new DuplicatedUsername("Username already exists");
        }
        if(userRepository.existsByPhoneOrEmail(userDTO.getPhone(), userDTO.getEmail())) {
            throw new AccountExists("Account already exists");
        }
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        users.setPassword(encodedPassword);
        users.setRoles("PATIENT");
        PatientEntity patient = PatientEntity.builder()
                .address(userDTO.getAddress())
                .status(userDTO.getStatus())
                .users(users)
                .build();
        users.setListPatient(List.of(patient));
        userRepository.save(users);
    }

    @Override
    public String Login(UserDTOLogin userDTO)  {
        UserEntity user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new DataNotFoundException("Username Or PassWord exists");
        }
        if(!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong phone number or password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDTO.getUsername(), userDTO.getPassword(),
                user.getAuthorities()
        );

        authenticationManager.authenticate(authenticationToken);
        try {
            return jwtTokenUtil.generateToken(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ChangePassword(PasswordDTO passwordDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username);
        if(!passwordEncoder.matches(passwordDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        if (!passwordDTO.getNewPassword().equalsIgnoreCase(passwordDTO.getConfirmPassword())){
            throw new BadCredentialsException("Wrong new password");
        }
        user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userRepository.save(user);
    }
}
