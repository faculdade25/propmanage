import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { JwtPayload } from 'jwt-decode';
import {
    HlmCardContentDirective,
    HlmCardDescriptionDirective,
    HlmCardDirective,
    HlmCardHeaderDirective,
    HlmCardTitleDirective,
} from '@spartan-ng/ui-card-helm';

import { HlmButtonDirective } from '@spartan-ng/ui-button-helm';
import { HlmInputDirective } from '@spartan-ng/ui-input-helm';
import { HlmLabelDirective } from '@spartan-ng/ui-label-helm';
import { HlmFormFieldModule } from '@spartan-ng/ui-formfield-helm';
import { environment } from '../../../enviroments/enviroment';
import axios, { AxiosResponse } from 'axios';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';


interface CustomJwtPayload {
    Role: string;
    exp: number;
}

@Component({
    selector: 'app-login-menu',
    standalone: true,
    imports: [CommonModule, FormsModule, HlmCardContentDirective,
        HlmCardDescriptionDirective,
        HlmCardDirective,
        HlmCardHeaderDirective,
        HlmCardTitleDirective,
        HlmButtonDirective,
        HlmInputDirective,
        HlmLabelDirective,
        HlmFormFieldModule
    ],
    providers: [CookieService],
    templateUrl: './login-menu.component.html',
    styleUrls: ['./login.component.scss']
})

export class LoginMenuComponent {

    email: string = '';
    password: string = '';

    constructor(private cookieService: CookieService, private router: Router) {
        this.checkAuth();
    }

    checkAuth() {
        const jwt = this.getCookie();
        if (jwt && this.isJwtValid(jwt)) {
            this.redirecter(jwt);
        }
    }

    setCookie(data: string) {
        this.cookieService.set('acs', data, { expires: 1, sameSite: 'Lax' });
    }

    getCookie(): string {
        return this.cookieService.get('acs');
    }

    isJwtValid(token: string): boolean {
        try {
            const decoded = jwtDecode<CustomJwtPayload>(token);
            return decoded.exp * 1000 > Date.now();
        } catch (error) {
            return false;
        }
    }

    redirecter(token: string) {
        const decoded = jwtDecode<CustomJwtPayload>(token);
        console.log(decoded);
        if (decoded.Role === 'ADMIN') {
            this.router.navigate(['/admin/dashboard']);
        } else {
            this.router.navigate(['/user']);
        }
    }

    onSubmit() {
        const loginData = { email: this.email, password: this.password };

        axios.post(`${environment.apiUrl}/api/v1/user/login`, loginData)
            .then((response: AxiosResponse) => {
                this.setCookie(response.data);
                this.checkAuth(); 
            })
            .catch((error) => {
                console.error('Login error:', error);
            });
    }
}