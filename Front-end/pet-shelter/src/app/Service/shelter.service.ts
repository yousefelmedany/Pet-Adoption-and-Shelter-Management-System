import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const baseUrl = 'http://localhost:8080/shelter';


@Injectable({
  providedIn: 'root'
})
export class ShelterService {

  constructor(private http: HttpClient) { }
  saveShelter(shelter: any): Observable<any> {
    return this.http.post(baseUrl + '/save', shelter);
  }
}
