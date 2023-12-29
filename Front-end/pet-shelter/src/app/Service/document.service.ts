import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Form } from '@angular/forms';
const baseUrl = 'http://localhost:8080/document';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

constructor(private http: HttpClient) { }
saveDocument(formdata:FormData): Observable<any> {
  return this.http.post<any>(baseUrl + '/save', formdata);
}
getAllDocumentsInPet(petId:any): Observable<any> {
  return this.http.get(baseUrl + '/getDocumentsByPetId',{params:{petid:petId}});
}
deleteDocument(documentId:any): Observable<any> {
  return this.http.delete<any>(baseUrl + '/delete',{params:{documentid:documentId}});
}
}
