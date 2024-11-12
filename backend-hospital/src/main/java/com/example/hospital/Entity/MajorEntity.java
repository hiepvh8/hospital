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
@Table(name ="major")
public class MajorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String image;
    Integer status;

    @OneToMany(mappedBy ="major" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<DoctorEntity> listDoctor= new ArrayList<>();

    @OneToMany(mappedBy ="majors" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<DiseasesMajor> listDiseasesMajor= new ArrayList<>();

    @OneToMany(mappedBy ="majors" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<ServicesEntity> listService = new ArrayList<>();

    @OneToMany(mappedBy ="majors" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<ArticleEntity> listArticle = new ArrayList<>();
}
