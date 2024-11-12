package com.example.hospital.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name ="medical_file")
public class MedicalFilesEntity extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Integer status;

    @Column(name ="patient_illness")
    String patientIllness;

    @Column(name ="treatment_process")
    String treatmentProcess;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

}
