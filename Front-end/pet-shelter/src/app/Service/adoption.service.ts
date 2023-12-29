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
    adopter_id: any,
    pet_id: any,
    des: any
  ): Observable<any> {
    return this.http.post<any>(`${baseUrl}/makeapplication`, {params: { adopterid: adopter_id, petid: pet_id, description: des }});
  }
}
