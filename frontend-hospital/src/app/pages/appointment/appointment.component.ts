import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ServiceTokenService } from '../../callApi/service-token.service';

@Component({
  selector: 'app-appointment',
  standalone: true,
  imports: [RouterModule,HttpClientModule,FormsModule,CommonModule],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.scss'
})
export class AppointmentComponent {
  Data = {
    appointmentDate: '',
    note: ''
  };

  constructor(private http: HttpClient, private router: Router , private token : ServiceTokenService){
  }

  onSubmit() {
    const token = this.token.getToken();
    const headers = new HttpHeaders()
    .set('Authorization', `Bearer ${token}`)
    .set('Content-Type', 'application/json');


    this.http.post('http://localhost:8080/hospital/users/patient/appointment', this.Data, {
      headers,
      withCredentials: true
    }).subscribe({
      next: (response) => {
        console.log('successful', response);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.log(token);
        console.error('failed', error);
      }
    });
  }

}
