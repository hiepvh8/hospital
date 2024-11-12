import { Component, OnInit } from '@angular/core';
import { ServiceApiService } from '../../callApi/service-api.service';
import { HttpClientModule } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-major',
  standalone: true,
  imports: [ RouterModule,HttpClientModule,FormsModule,CommonModule],
  templateUrl: './major.component.html',
  styleUrls: ['./major.component.scss']  // Chỉnh lại 'styleUrls' đúng cú pháp
})
export class MajorComponent implements OnInit {
  major: any[] = []; // Mảng lưu danh sách chuyên khoa

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
  }

  
}
