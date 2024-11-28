import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { NgModule } from '@angular/core';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { MajorComponent } from './pages/major/major.component';
import { DoctorComponent } from './pages/doctor/doctor.component';
import { HttpClientModule } from '@angular/common/http';
import { ServicesHospitalComponent } from './pages/servicesHospital/services.component';
import { AppointmentComponent } from './pages/appointment/appointment.component';

export const routes: Routes = [
    { path: '', component: HomeComponent},
    { path: 'login', component: LoginComponent},
    { path: 'register', component: RegisterComponent},
    { path: 'major', component: MajorComponent},
    { path: 'services', component: ServicesHospitalComponent},
    { path: 'doctor', component: DoctorComponent},
    { path: 'appointment', component: AppointmentComponent},

];

@NgModule({
    imports: [RouterModule.forRoot(routes),HttpClientModule],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }