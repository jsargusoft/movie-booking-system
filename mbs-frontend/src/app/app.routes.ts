import { Routes } from '@angular/router';
import { SideImageLayoutComponent } from './utils/side-image-layout/side-image-layout.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ForgotPasswordComponent } from './components/fogotPassword/forgotPassword.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';

export const routes: Routes = [
    {path: '', redirectTo:'login',pathMatch:'full'},
    {path: '', component: SideImageLayoutComponent,
      children: [
        {path: 'login', component: LoginComponent},
        {path: 'register', component: RegisterComponent},
        {path: 'forgot-password', component: ForgotPasswordComponent},
        {path: 'reset-password', component: ResetPasswordComponent},
      ]
      },
     {path: 'dashboard', component: DashboardComponent}
];
