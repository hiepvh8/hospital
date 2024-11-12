import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})
export class ServiceTokenService {

  constructor() { }

  private readonly tokenKey = 'auth_token'; // Key lưu trữ token trong Local Storage

  // Lưu token vào Local Storage
  saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  // Lấy token từ Local Storage
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // Xóa token khỏi Local Storage
  removeToken(): void {
    localStorage.removeItem(this.tokenKey);
  }

  // Kiểm tra xem đã có token hay chưa
  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
