import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const baseUrl = 'http://localhost:8080/adopter';

@Injectable({
  providedIn: 'root',
})
export class AdoptionService {
  constructor(private http: HttpClient) {}

  make_adoption_request(
    formdata: FormData,
  ): Observable<any> {
    return this.http.post<any>(`${baseUrl}/makeapplication`, formdata);
  }
  getApplicationsByAdopterId(adopterId:any): Observable<any> {
    return this.http.get<any>(`${baseUrl}/getapplications`,{params:{adopterid:adopterId}});
  }
}
