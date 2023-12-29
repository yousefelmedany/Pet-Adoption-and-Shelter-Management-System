import { Injectable } from '@angular/core';
import { Document } from '../Objects/Document';
import { Shelter } from '../Objects/Shelter';
@Injectable({
  providedIn: 'root'
})
export class SharedService {

  documents:Document[]=[];
  choosedShelter!:Shelter;
  setDocuments(documents:Document[]){
    this.documents=documents;
  }
  getDocuments(){
    return this.documents;
  }
  setChoosedShelter(shelter:Shelter){
    this.choosedShelter=shelter;
  }
  getChoosedShelter(){
    return this.choosedShelter;
  }
}
