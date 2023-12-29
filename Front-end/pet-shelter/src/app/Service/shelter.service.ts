import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const baseUrl = 'http://localhost:8080/shelter';


@Injectable({
  providedIn: 'root'
})
export class ShelterService {

  constructor(private http: HttpClient) { }
  saveShelter(shelter: any,id:number): Observable<any> {
    return this.http.post(baseUrl + '/save',shelter,{params:{id:id}});
  }
  deleteShelter(id:number):Observable<any>{
    return this.http.delete(baseUrl+'/delete',{params:{shelterid:id}})
  }
  editShelter(shelter:any):Observable<any>{
    return this.http.put(baseUrl+'/edit',shelter);
  }
  getAllShelters(): Observable<any> {
    return this.http.get<any>(baseUrl + '/getall');
  }
  getSheltersNames(): Observable<any> {
    return this.http.get<any>(baseUrl + '/getShelterNames');
  }
  

}
