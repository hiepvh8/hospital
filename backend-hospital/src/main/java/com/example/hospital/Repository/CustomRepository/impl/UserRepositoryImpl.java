package com.example.hospital.Repository.CustomRepository.impl;

import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Model.request.DoctorRequest;
import com.example.hospital.Model.request.PatientRequest;
import com.example.hospital.Repository.CustomRepository.UserRepositoryCustom;
import com.example.hospital.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static String CheckQuery(PatientRequest patientRequest){
        StringBuilder x = new StringBuilder("");
        try {
            Field[] fields = PatientRequest.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String key = item.getName();
                if(!key.equals("patientIllness")  ) {
                    Object value = item.get(patientRequest);
                    if (value != null && !value.toString().equalsIgnoreCase("")) {
                        x.append( " and b." + key + " like '%" + value+"%' ");
                    }
                }else if (key.equals("patientIllness")){
                    Object value = item.get(patientRequest);
                    if (value != null && !value.toString().equalsIgnoreCase("")) {
                        x.append(" and MEDICAL_FILE.patient_illness like '%" + patientRequest.getPatientIllness() + "%' ");
                    }
                }

            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return x.toString();
    }

    public static String CheckJoin(PatientRequest patientRequest){
        StringBuilder x = new StringBuilder("");
        if(patientRequest.getPatientIllness() != null && !patientRequest.getPatientIllness().equals("")) {
            x.append(" JOIN PATIENT ON b.id = PATIENT.user_id ");
            x.append(" JOIN MEDICAL_FILE ON PATIENT.id = MEDICAL_FILE.patient_id ");

        }

        return x.toString();
    }

    public static String CheckQuery(DoctorRequest doctorRequest){
        StringBuilder x = new StringBuilder("");
        try {
            Field[] fields = DoctorRequest.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String key = item.getName();
                if(!key.equals("position") && !key.equals("major") && !key.equals("service") ) {
                    Object value = item.get(doctorRequest);
                    if (value != null && !value.toString().equalsIgnoreCase("")) {
                            x.append( " and b." + key + " like '%" + value+"%' ");
                    }
                }else if (key.equals("position")){
                    Object value = item.get(doctorRequest);
                    if (value != null && !value.toString().equalsIgnoreCase("")) {
                        x.append(" and DOCTOR.position like '%" + doctorRequest.getPosition() + "%' ");
                    }

                }else if (key.equals("major")){
                    Object value = item.get(doctorRequest);
                    if (value != null && !value.toString().equalsIgnoreCase("")) {
                    x.append(" and MAJOR.id = "+doctorRequest.getMajor()+" ");}
                }else {
                    Object value = item.get(doctorRequest);
                    if (value != null && !value.toString().equalsIgnoreCase("")) {
                    x.append(" and SERVICE.name like '%"+doctorRequest.getService()+"%' ");}
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return x.toString();
    }
    public static String CheckJoin(DoctorRequest doctorRequest){
        StringBuilder x = new StringBuilder("");
        if(doctorRequest.getPosition() != null && !doctorRequest.getPosition().equals("")) {
            x.append(" JOIN DOCTOR ON b.id = DOCTOR.user_id ");
            if(doctorRequest.getMajor() != null ) {
                x.append(" JOIN MAJOR ON DOCTOR.major_id = MAJOR.id ");
                if(doctorRequest.getService()!= null && !doctorRequest.getService().equals("")) {
                    x.append(" JOIN SERVICE ON MAJOR.id = SERVICE.major_id ");
                }
            }else {
                if(doctorRequest.getService()!= null && !doctorRequest.getService().equals("")) {
                    x.append(" JOIN MAJOR ON DOCTOR.major_id = MAJOR.id ");
                    x.append(" JOIN SERVICE ON MAJOR.id = SERVICE.major_id ");
                }
            }
        } else {
            if(doctorRequest.getMajor() != null && !doctorRequest.getMajor().equals("")) {
                x.append(" JOIN DOCTOR ON b.id = DOCTOR.user_id ");
                x.append(" JOIN MAJOR ON DOCTOR.major_id = MAJOR.id ");
                if(doctorRequest.getService()!= null && !doctorRequest.getService().equals("")) {
                    x.append(" JOIN SERVICE ON MAJOR.id = SERVICE.major_id ");
                }
            }else {
                if(doctorRequest.getService()!= null && !doctorRequest.getService().equals("")) {
                    x.append(" JOIN DOCTOR ON b.id = DOCTOR.user_id ");
                    x.append(" JOIN MAJOR ON DOCTOR.major_id = MAJOR.id ");
                    x.append(" JOIN SERVICE ON MAJOR.id = SERVICE.major_id ");
                }
            }
        }
        return x.toString();
    }


    @Override
    public List<UserEntity> findPatientByRequest(PatientRequest patientRequest) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM USERS b ");
        sql.append(CheckJoin(patientRequest));
        sql.append(" WHERE b.roles like '%PATIENT%' AND b.status = 1 ");
        sql.append(CheckQuery(patientRequest));
        sql.append(" group by  b.id ");
        Query quey = entityManager.createNativeQuery(sql.toString(),UserEntity.class);
        List<UserEntity> arr = quey.getResultList();
        return quey.getResultList();
    }

    @Override
    public List<UserEntity> findDoctorByRequest(DoctorRequest doctorRequest) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM USERS b ");
        sql.append(CheckJoin(doctorRequest));
        sql.append(" WHERE b.roles like '%DOCTOR%' AND b.status = 1 ");
        sql.append(CheckQuery(doctorRequest));
        sql.append(" group by  b.id ");
        Query quey = entityManager.createNativeQuery(sql.toString(),UserEntity.class);
        List<UserEntity> arr = quey.getResultList();
        return quey.getResultList();
    }
}
