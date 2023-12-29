import { Component, OnInit } from '@angular/core';
import { PetService } from '../Service/pet.service';
import { ShelterService } from '../Service/shelter.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AdoptionService } from '../Service/adoption.service';
import { SharedService } from '../Service/shared.service';
import { Pet } from '../Objects/Pet';
import { Shelter } from '../Objects/Shelter';
declare const $: any;

@Component({
  selector: 'app-manage-adoption',
  templateUrl: './manage-adoption.component.html',
  styleUrls: ['./manage-adoption.component.css'],
})
export class ManageAdoptionComponent implements OnInit {
  constructor(
    private petService: PetService,
    private shelterService: ShelterService,
    private router: Router,
    private sanitizer: DomSanitizer,
    private adoptionService: AdoptionService,
    private sharedService: SharedService
  ) {}

  ngOnInit(): void {}

  pets: Pet[] = [];
  spinner_flag: boolean = false;
  newPet: Pet = new Pet();
  selectedFile!: File;
  images: any[] = [];
  description: any = '';
  currentShelter: Shelter = this.sharedService.getChoosedShelter();

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
