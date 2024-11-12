import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DoctorApiService {
  private appiUrlDoctor = 'http://localhost:8080/hospital/users/doctor';

  constructor(private http: HttpClient) {}

  getDoctor(): Observable<any[]>{
    return this.http.get<any[]>(this.appiUrlDoctor);
  }
}
