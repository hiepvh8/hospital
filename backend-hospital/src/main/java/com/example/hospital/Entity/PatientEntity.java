package com.example.hospital.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name ="patient")
public class PatientEntity extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String address;
    Integer status;

    @OneToMany(mappedBy ="patient" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<AppointmentsEntity> listAppointments= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @OneToMany(mappedBy ="patient" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<MedicalFilesEntity> listMedicalFiles= new ArrayList<>();

}
