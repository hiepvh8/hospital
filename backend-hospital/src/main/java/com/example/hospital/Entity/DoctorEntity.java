package com.example.hospital.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name ="doctor")
public class DoctorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String degree;
    @Column(name ="number_of_review")
    Integer numberOfReviews;

    @Column(name ="total_points")
    Long totalScore;
    String image ;
    String position;
    Integer status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private MajorEntity major;

    @OneToMany(mappedBy ="doctor" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<DoctorSchedule> listDoctorSchedule= new ArrayList<>();

    @OneToMany(mappedBy ="doctors" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<AppointmentsEntity> listAppointments= new ArrayList<>();



}
