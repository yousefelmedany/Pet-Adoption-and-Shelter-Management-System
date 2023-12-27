import { Component, OnInit } from '@angular/core';
import { PetService } from '../Service/pet.service';
import { ShelterService } from '../Service/shelter.service';
import { Router } from '@angular/router';
import { Pet } from '../Objects/Pet';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
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
    private sanitizer: DomSanitizer
  ) {}
pets: Pet[] = [];
spinner_flag: boolean = false;
newPet: Pet = new Pet();
selectedFile!: File;
images: any[] = [];
  ngOnInit(): void {
    this.petService.getAllPetsInShelter(5).subscribe(
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
    $('#notify').modal('hide');
  }
Addpet() {
  this.spinner_flag = true;
  let formdata = new FormData();
  formdata.append('file', this.selectedFile);
  formdata.append('pet', JSON.stringify(this.newPet));
  formdata.append('shelterid', '5');
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

handleImageInput(event: any): void {
  const file = event.target.files[0]; // Get the selected file
  if (file) {
    this.selectedFile = file;
  
  }
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
