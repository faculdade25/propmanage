import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const cookieService = inject(CookieService);
  
  // Verificar se a requisição é de login - assumindo que a URL contém 'login'
  if (req.url.includes('/api/v1/user/login')){
    // Se for login, deixa passar sem modificação
    return next(req);
  }
  
  // Obter o token JWT do cookie
  const token = cookieService.get('acs');
  
  // Se o token existe, adiciona ele ao cabeçalho Authorization
  if (token) {
    const clonedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    
    // Processa a requisição modificada
    return next(clonedReq);
  }
  
  // Se não há token, processa a requisição original
  return next(req);
};