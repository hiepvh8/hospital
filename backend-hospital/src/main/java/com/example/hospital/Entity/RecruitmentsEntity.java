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
@Table(name ="recruitment")
public class RecruitmentsEntity extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="job_vacancy")
    String jobVacancy;

    @Column(name ="start_date")
    Date startDate ;

    @Column(name ="end_date")
    Date endDate ;
    String salary;
    String required ;
    String describes ;
    Integer status;
}
