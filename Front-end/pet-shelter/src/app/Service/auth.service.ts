import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { StorageService } from './storage.service';


const AUTH_API = environment.api_url;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient,private cookieService: CookieService,private storage: StorageService) {}
  login(body:any): Observable<any> {
    return this.http.post(
      AUTH_API + '/auth/login',body,
      httpOptions
    );
  }
  register(user:any): Observable<any> {
    return this.http.post(
      AUTH_API + '/auth/register',user,
      httpOptions
    );
  }
  logout(): Observable<any> {
    this.cookieService.deleteAll("jwtCookie");
    return this.http.post(AUTH_API+ '/auth/logout', httpOptions);
  }
  getCurrentUser(): Observable<any> {
    let user=this.storage.getUser()
    let id =user.personId
    return this.http.post(AUTH_API + '/auth/getMe',user);
  }

  //make get http request with a body in it



}
