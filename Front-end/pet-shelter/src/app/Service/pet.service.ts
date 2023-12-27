import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const baseUrl = 'http://localhost:8080/pet';

@Injectable({
  providedIn: 'root'
})
export class PetService {

  constructor(private http: HttpClient) { }
  savePet(pet: any,shelterId:any): Observable<any> {
    return this.http.post(baseUrl + '/save', pet,{params:{shelterid:shelterId}});
  }
  getAllPetsInShelter(shelterId:any): Observable<any> {
    return this.http.get(baseUrl + '/get',{params:{shelterid:shelterId}});
  }
}
