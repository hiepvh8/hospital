import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private isLoggedIn: boolean = false;

  // Gọi khi người dùng đăng nhập thành công
  login() {
    this.isLoggedIn = true;
  }

  // Gọi khi người dùng đăng xuất
  logout() {
    this.isLoggedIn = false;
  }

  // Kiểm tra trạng thái đăng nhập
  getLoginStatus(): boolean {
    return this.isLoggedIn;
  }
}
