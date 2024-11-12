import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ServiceApiService } from '../../callApi/service-api.service';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [RouterModule,HttpClientModule,FormsModule,CommonModule],
  templateUrl: './services.component.html',
  styleUrl: './services.component.scss'
})
export class ServicesHospitalComponent implements OnInit {
  major: any[] = [];
  services : any[] =[];

  constructor(private serviceApi: ServiceApiService) {}

  ngOnInit(): void {
    this.serviceApi.getMajors().subscribe(
      response => {
        this.major = response;
        console.log(response);
      },
      error => {
        console.error(' failed', error);
      }
    );

    this.serviceApi.getService().subscribe(
      response => {
        this.services = response;
        console.log(response);
      },
      error => {
        console.error(' failed', error);
      }
    );
  }

}
