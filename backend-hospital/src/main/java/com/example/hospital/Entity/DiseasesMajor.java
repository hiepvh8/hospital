package com.example.hospital.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name ="diseases_major")
public class DiseasesMajor extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Integer status;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private MajorEntity majors;

    @ManyToOne
    @JoinColumn(name = "diseases_id")
    private DiseasesEntity diseases;
}
