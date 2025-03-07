import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
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
    

    constructor(private cookieService: CookieService, private router: Router) {
        this.checkAuth()
    }

    checkAuth(){
        this.getCookie();
        if(this.cookieService.get('acs') != ''){
           this.router.navigate(['/home']);
        }
    }

    setCookie(data: string) {
        this.cookieService.set('acs', data, { expires: 1, sameSite: 'Lax' });
    }

    getCookie() {
        const token = this.cookieService.get('acs');
    }

    email: string = '';
    password: string = '';

    onSubmit() {
        const loginData = {
            email: this.email,
            password: this.password
        };

        axios.post(`${environment.apiUrl}/api/v1/user/login`, loginData)
            .then((response: AxiosResponse) => {
                this.setCookie(response.data);
                this.getCookie();
            })
            .catch((error) => {
                console.error('Login error:', error);
            });
    }
}