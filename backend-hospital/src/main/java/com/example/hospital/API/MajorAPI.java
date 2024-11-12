package com.example.hospital.API;

import com.example.hospital.Model.dto.ArticleDTO;
import com.example.hospital.Model.dto.DiseasesDTO;
import com.example.hospital.Model.dto.MajorDTO;
import com.example.hospital.Model.dto.ServiceDTO;
import com.example.hospital.Model.request.AppointmentRequest;
import com.example.hospital.Model.request.ArticleRequest;
import com.example.hospital.Model.request.ServiceRequest;
import com.example.hospital.Model.response.ArticleResponse;
import com.example.hospital.Model.response.ServiceResponse;
import com.example.hospital.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital/major/")
public class MajorAPI {
    @Autowired
    private MajorService majorService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private DiseasesService diseasesService;

    @PostMapping
    public void AddMajor (@RequestBody MajorDTO majorDTO){
        majorService.addMajor(majorDTO);
    }

    @PostMapping("/service")
    public void AddService (@RequestBody ServiceDTO serviceDTO){
        serviceService.addService(serviceDTO);
    }

    @PostMapping("/service/appointment")
    public void MakeAppointment (@RequestBody ServiceRequest serviceRequest){
        patientService.MakeService(serviceRequest);
    }

    @GetMapping("/article")
    public List<ArticleResponse> FindArticle (@RequestBody ArticleRequest articleRequest){
        return articleService.findByRequest(articleRequest);
    }

    @GetMapping
    public List<MajorDTO> ListMajor (){
        return majorService.findAllMajors();
    }

    @GetMapping("/service/{id}")
    public List<ServiceResponse> ListService ( @PathVariable Long id){
        return serviceService.findServiceByMajor(id);
    }

    @GetMapping("/diseases/{s}")
    public List<DiseasesDTO> listDiseases (@PathVariable String s){
        return diseasesService.findDiseasesByChar(s);
    }

    @PostMapping("/service/diseases")
    public void AddDiseases(@RequestBody DiseasesDTO diseasesDTO){
        diseasesService.AddDiseases(diseasesDTO);
    }

    @PostMapping("/article")
    public void AddArticle (@RequestBody ArticleDTO articleRequest){
        articleService.AddArticle(articleRequest);
    }
}
