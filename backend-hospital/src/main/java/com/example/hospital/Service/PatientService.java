package com.example.hospital.Service;



import com.example.hospital.Model.request.AppointmentRequest;
import com.example.hospital.Model.request.ServiceRequest;

import java.util.List;

public interface PatientService {
    void MakeAppointment (AppointmentRequest appointmentRequest);
    void MakeService(ServiceRequest serviceRequest);

}
