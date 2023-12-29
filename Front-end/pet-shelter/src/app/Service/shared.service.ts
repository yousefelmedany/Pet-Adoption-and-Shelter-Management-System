import { Injectable } from '@angular/core';
import { Document } from '../Objects/Document';
@Injectable({
  providedIn: 'root'
})
export class SharedService {

  documents:Document[]=[];
  setDocuments(documents:Document[]){
    this.documents=documents;
  }
  getDocuments(){
    return this.documents;
  }
}
