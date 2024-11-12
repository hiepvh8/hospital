import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule,HttpClientModule,FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  registerData = {
    username: '',
    password: '',
    email: '',
    phone: '',
    fullname: '',
    address: '',
    date_of_birth: ''
  };

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    this.http.post('http://localhost:8080/hospital/users/register', this.registerData, { headers})
      .subscribe(
        response => {
          console.log('Register successful', response);

          this.router.navigate(['/login']);
        },
        error => {
          console.error('Register failed', error);
        }
      );
  }

}
