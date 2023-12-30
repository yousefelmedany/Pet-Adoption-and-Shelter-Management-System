import { Component, OnInit } from '@angular/core';
import { PetService } from '../Service/pet.service';
import { ShelterService } from '../Service/shelter.service';
import { Router } from '@angular/router';
import { Pet } from '../Objects/Pet';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Document } from '../Objects/Document';
import { DocumentService } from '../Service/document.service';
import { SharedService } from '../Service/shared.service';
import { StorageService } from '../Service/storage.service';
import { StaffService } from '../Service/staff.service';
import { Staff } from '../Objects/Staff';
declare const $: any;
@Component({
  selector: 'app-managepets',
  templateUrl: './managepets.component.html',
  styleUrls: ['./managepets.component.css']
})
export class ManagepetsComponent implements OnInit {

  constructor(
    private petService: PetService,
    private shelterService: ShelterService,
    private router: Router,
    private sanitizer: DomSanitizer,
    private documentService:DocumentService,
    private sharedService:SharedService,
    private storageService:StorageService,
    private staffService:StaffService
  ) {}
pets: Pet[] = [];
spinner_flag: boolean = false;
newPet: Pet = new Pet();
selectedFile!: File;
images: any[] = [];
document:Document=new Document();
PetDocuments:Document[]=[];
index:number=0;
currStaff:Staff=this.storageService.getStaff();
ngOnInit(): void {
  this.petService.getAllPetsInShelter(this.currStaff.staff.shelterId).subscribe(
      (data) => {
        this.pets = data;
        for(let i=0;i<this.pets.length;i++){
          this.images.push(this.convertToImage(this.pets[i].image));
        }
        console.log(this.pets);
      },
      (error) => {
        console.log(error);
      }
    );

    
  }
  close() {
    $('#exampleModalCenter').modal('hide');
    $('#document').modal('hide');
    $('#notify').modal('hide');
    $('#notify1').modal('hide');
    $('#edit').modal('hide');
  }
  setindex(i:number){
    this.index=i;
  }
  getPetDocuments(i:number){
    this.documentService.getAllDocumentsInPet(this.pets[i].petId).subscribe(res=>{
      this.PetDocuments=res;
      this.sharedService.setDocuments(this.PetDocuments);
      this.router.navigate(['/staffpage/documentpage']);
    });
  }
Addpet() {
  this.spinner_flag = true;
  let formdata = new FormData();
  formdata.append('file', this.selectedFile);
  formdata.append('pet', JSON.stringify(this.newPet));
  formdata.append('shelterid', this.currStaff.staff.shelterId);
  this.petService.savePet(formdata).subscribe(
    (data) => {
      this.spinner_flag = false;
      let temp:Pet=data;
      this.pets.push(temp);
      this.newPet = new Pet();
      $('#exampleModalCenter').modal('hide');
      $('#notify').modal('show');
    },
    (error) => {
      this.spinner_flag = false;
      console.log(error);
    }
  );
}
AddDocument(){
  this.spinner_flag = true;
  let formdata = new FormData();
  formdata.append('file', this.selectedFile);
  formdata.append('document', JSON.stringify(this.document));
  formdata.append('petid', this.pets[this.index].petId);
  this.documentService.saveDocument(formdata).subscribe(
    (data) => {
      this.spinner_flag = false;
      this.document=new Document();
      $('#document').modal('hide');
      $('#notify').modal('show');
    },
    (error) => {
      this.spinner_flag = false;
      console.log(error);
    }
  );

}

handleImageInput(event: any): void {
  const file = event.target.files[0]; // Get the selected file
  if (file) {
    this.selectedFile = file;
  
  }
}
Editpet(){
  this.spinner_flag = true;
  this.petService.editPet(this.pets[this.index]).subscribe(
    (data) => {
      this.spinner_flag = false;
      $('#edit').modal('hide');
      $('#notify1').modal('show');
    },
    (error) => {
      this.spinner_flag = false;
      console.log(error);
    }
  );
}
removepet(i:number){
  this.spinner_flag = true;
  this.petService.removePet(this.pets[i].petId).subscribe(
    (data) => {
      this.spinner_flag = false;
      this.pets.splice(i,1);
    },
    (error) => {
      this.spinner_flag = false;
      console.log(error);
    }
  );
}
convertToImage(string: any) {
  const binaryString = atob(string);
  const binaryData = new Uint8Array(binaryString.length);
  for (let i = 0; i < binaryString.length; i++) {
    binaryData[i] = binaryString.charCodeAt(i);
  }
  const blob = new Blob([binaryData], { type: 'application/image' });
  const blobUrl = URL.createObjectURL(blob);
  return this.sanitizer.bypassSecurityTrustUrl(blobUrl) as SafeUrl;
}
}
