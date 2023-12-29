import { Component, OnInit } from '@angular/core';
import { PetService } from '../Service/pet.service';
import { ShelterService } from '../Service/shelter.service';
import { Router } from '@angular/router';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Pet } from '../Objects/Pet';
declare const $: any;

@Component({
  selector: 'app-adoption-page',
  templateUrl: './adoption-page.component.html',
  styleUrls: ['./adoption-page.component.css'],
})
export class AdoptionPageComponent implements OnInit {
  constructor(
    private petService: PetService,
    private shelterService: ShelterService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.petService.getAllPetsInShelter(5).subscribe(
      (data) => {
        this.pets = data;
        for (let i = 0; i < this.pets.length; i++) {
          this.images.push(this.convertToImage(this.pets[i].image));
        }
        console.log(this.pets);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  pets: Pet[] = [];
  spinner_flag: boolean = false;
  newPet: Pet = new Pet();
  selectedFile!: File;
  images: any[] = [];

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
