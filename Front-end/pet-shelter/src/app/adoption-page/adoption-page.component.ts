import { Component, OnInit } from '@angular/core';
import { PetService } from '../Service/pet.service';
import { ShelterService } from '../Service/shelter.service';
import { Router } from '@angular/router';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Pet } from '../Objects/Pet';
import { AdoptionService } from '../Service/adoption.service';
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
    private sanitizer: DomSanitizer,
    private adoptionService: AdoptionService
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
  description: any = 'ok';
  index: any = 0;

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
  set_index(i: any) {
    this.index = i;
  }
  fill_application() {
    this.adoptionService
      .make_adoption_request(10, this.pets[this.index].petId, this.description)
      .subscribe((res) => {
        console.log(res);
      });
  }
}
