import { Component, OnInit } from '@angular/core';
import { PetService } from '../Service/pet.service';
import { ShelterService } from '../Service/shelter.service';
import { Router } from '@angular/router';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Pet } from '../Objects/Pet';
import { AdoptionService } from '../Service/adoption.service';
import { Application } from '../Objects/Application';
import { StorageService } from '../Service/storage.service';
import { Adopter } from '../Objects/Adopter';
declare const $: any;

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css'],
})
export class ApplicationComponent implements OnInit {
  constructor(
    private petService: PetService,
    private shelterService: ShelterService,
    private router: Router,
    private sanitizer: DomSanitizer,
    private adoptionService: AdoptionService,
    private storageService: StorageService
  ) {}

  spinner_flag: boolean = false;
  newPet: Pet = new Pet();
  selectedFile!: File;
  images: any[] = [];
  applications:Application[]=[];
  isApproved: boolean = false;
  isRejected: boolean = true;
  isPending: boolean = false;
  currAdopter:Adopter=this.storageService.getAdopter();
  ngOnInit(): void {
    this.adoptionService.getApplicationsByAdopterId(this.currAdopter.adopterId).subscribe(res=>{
      this.applications=res;
      for(let i=0;i<this.applications.length;i++){
        this.images.push(this.convertToImage(this.applications[i].pet.image));
      }
      console.log(this.applications);
    });
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

  close() {
    $('#exampleModalCenter').modal('hide');
    $('#notify').modal('hide');
  }
}
