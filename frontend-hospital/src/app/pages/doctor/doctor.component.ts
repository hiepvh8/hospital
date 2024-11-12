import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DoctorApiService } from '../../callApi/doctor-api.service';
import { ServiceApiService } from '../../callApi/service-api.service';

@Component({
  selector: 'app-doctor',
  standalone: true,
  imports: [RouterModule,HttpClientModule,FormsModule,CommonModule],
  templateUrl: './doctor.component.html',
  styleUrl: './doctor.component.scss'
})
export class DoctorComponent implements OnInit {
  doctor : any[] =[];
  services  : any[] = [];
  major : any[] = [];

  constructor(private doctorApi: DoctorApiService ,  private serviceApi : ServiceApiService) {}

  ngOnInit(): void {
    this.doctorApi.getDoctor().subscribe(
      response => {
        this.doctor = response;
      },
      error => {
        console.log("failed", error);
      }
    );
    
    this.serviceApi.getService().subscribe(
      response => {
        this.services = response;
      },
      error => {
        console.log("failed" , error);
      }
    );

    this.serviceApi.getMajors().subscribe(
      response => {
        this.major = response;
      },
      error => {
        console.log("failed" , error)
      }
    );
  }

}
