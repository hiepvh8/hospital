import { Injectable } from '@angular/core';
import { ServiceTokenService } from './service-token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private token : ServiceTokenService) {}

  private isLoggedIn: boolean = false;

  // Gọi khi người dùng đăng nhập thành công
  login() {
      this.isLoggedIn = true;
  }

  // Gọi khi người dùng đăng xuất
  logout() {
    this.isLoggedIn = false;
    console.log("Đang xóa token...");
    this.token.removeToken();
  }

  // Kiểm tra trạng thái đăng nhập
  getLoginStatus(): boolean {
    if(this.token.getToken() != null){
      return true;
    }
    return false;
  }
}
