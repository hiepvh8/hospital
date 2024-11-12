import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthServiceService } from '../../callApi/auth-service.service';
import { ServiceTokenService } from '../../callApi/service-token.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule,HttpClientModule,FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginData = {
    username: '',
    password: ''
  };

  constructor(private authService: AuthServiceService,private http: HttpClient, private router: Router , private tokenService : ServiceTokenService) {}

  onSubmit() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    this.http.post('http://localhost:8080/hospital/users/login', this.loginData, { headers, responseType: 'text' })
      .subscribe(
        response => {
          console.log('Login successful', response);
          this.tokenService.saveToken(response);
          this.authService.login();
          this.router.navigate(['/']);
        },
        error => {
          console.error('Login failed', error);
        }
      );
  }

}
