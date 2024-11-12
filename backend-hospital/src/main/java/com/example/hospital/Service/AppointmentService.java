package com.example.hospital.Service;

import com.example.hospital.Model.response.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    List<AppointmentResponse> listAppointments();
}
