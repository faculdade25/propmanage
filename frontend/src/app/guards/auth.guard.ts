import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private cookieService: CookieService) {}

  getCookie(): string {
    return this.cookieService.get('acs');
  }

  canActivate(): boolean {
    const token = this.getCookie();

    if (!token) {
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }
}