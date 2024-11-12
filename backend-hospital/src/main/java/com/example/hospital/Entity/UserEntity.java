package com.example.hospital.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name ="users")
public class UserEntity extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String username ;
    String password ;
    Integer status ;
    String phone ;
    String email ;
    String fullname ;

    @Column(name ="date_of_birth")
    Date date_of_birth ;
    String roles ;

    @OneToMany(mappedBy ="user" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<DoctorEntity> listDoctor= new ArrayList<>();

    @OneToMany(mappedBy ="users" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<AdminEntity> listAdmin= new ArrayList<>();

    @OneToMany(mappedBy ="users" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<PatientEntity> listPatient= new ArrayList<>();

    @OneToMany(mappedBy ="users" , fetch = FetchType.LAZY , cascade =  CascadeType.ALL , orphanRemoval =  true)
    private List<ReceptionistEntity> listReceptionist= new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = Arrays.asList(this.getRoles().split(" "));
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for(String role : roles){
            authorityList.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
//        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorityList;
    }
}
