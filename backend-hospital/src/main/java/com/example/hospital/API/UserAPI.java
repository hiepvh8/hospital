package com.example.hospital.API;

import com.example.hospital.Model.dto.*;
import com.example.hospital.Model.request.*;
import com.example.hospital.Model.response.AppointmentResponse;
import com.example.hospital.Model.response.DoctorResponse;
import com.example.hospital.Model.response.PatientResponse;
import com.example.hospital.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital/users/")
public class UserAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ReceptionistService receptionistService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/register")
    public void Register(@RequestBody UserDTORegister userDTO) {
        userService.Register(userDTO);
    }

    @PostMapping("/login")
    public String Login(@RequestBody UserDTOLogin userDTO) {
        return userService.Login(userDTO);
    }

    @PutMapping ("/password")
    public void ChangePassword (@RequestBody PasswordDTO passwordDTO){

    }


    @PostMapping("/patient/appointment")
    public void MakeAppointment( @RequestBody AppointmentRequest appointmentRequest) {
        patientService.MakeAppointment(appointmentRequest);

    }
    @GetMapping("/doctor")
    public List<DoctorResponse> findDoctor (DoctorRequest doctorRequest){
        return doctorService.findByRequest(doctorRequest);
    }

    @GetMapping("/patient")
    public List<PatientResponse> findPatient (PatientRequest patientRequest){
        return userService.findByRequest(patientRequest);
    }

    @PostMapping("/doctor")
    public void AddDoctor(@RequestBody DoctorDTO doctorDTO) {
        doctorService.addDoctor(doctorDTO);
    }

    @PostMapping("/receptionist")
    public void AddReceptionist (@RequestBody ReceptionistDTO receptionistDTO) {
        receptionistService.AddReceptionist(receptionistDTO);
    }

    @PutMapping ("/appointment")
    public void BorowesAppointment (@RequestBody BrowseAppoinmentRequest browseAppoinmentRequest){
        receptionistService.BrowseAppointment(browseAppoinmentRequest);
    }

    @GetMapping("/patient/appointment")
    public List<AppointmentResponse> findAppointmentNoBrowes(){
        return appointmentService.listAppointments();
    }



}
