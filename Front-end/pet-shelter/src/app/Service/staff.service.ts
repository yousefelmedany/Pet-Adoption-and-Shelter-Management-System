import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Staff } from '../Objects/Staff';
const baseurl = "http://localhost:8080/Staff";



@Injectable({
  providedIn: 'root'
})
export class StaffService {

  constructor(private http:HttpClient) { }

  GetPendingRequests(): Observable<any>{
      return this.http.get<any>(baseurl+'/getpendingRequests');
  }

  AcceptApplication(AdopterId:any, PetId:any):Observable<any>{
    return this.http.put<any>(baseurl+'/AcceptRequest', null,{params:{adopterid:AdopterId, petid:PetId}})
  }

  DeclineApplication(AdopterId:any, PetId:any):Observable<any>{
    console.log(AdopterId, PetId);
    return this.http.put<any>(baseurl+'/DeclineRequest',null, {params:{adopterid:AdopterId, petid:PetId}})
  
  }

  UpdateStaffMember(newstaff:Staff):Observable<any>{
    return this.http.put<any>(baseurl+'/updateStaff',newstaff)
  }
  getSherlterIdByStaffId(staffId:any):Observable<any>{
    return this.http.get<any>(baseurl+'/getShelterOfStaff',{params:{staffId:staffId}})
  }
    


}
