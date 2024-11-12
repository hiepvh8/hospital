import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceApiService {
  private apiUrlMajor = 'http://localhost:8080/hospital/major/'; // URL API
  private apiUrlService = 'http://localhost:8080/hospital/major/service';

  constructor(private http: HttpClient) {}

  getMajors(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrlMajor ); // Gọi API để lấy danh sách chuyên khoa
  }

  getService(): Observable<any[]>{
    return this.http.get<any[]>(this.apiUrlService); // Goi API lay danh sach dich vu
  }
}

