package com.example.hospital.Service;

import com.example.hospital.Model.dto.ReceptionistDTO;
import com.example.hospital.Model.request.BrowseAppoinmentRequest;

public interface ReceptionistService {
    void BrowseAppointment(BrowseAppoinmentRequest browseAppoinmentRequest);
    void AddReceptionist (ReceptionistDTO receptionistDTO);
}
