import { Component, OnInit } from '@angular/core';
import { PetService } from '../Service/pet.service';
import { ShelterService } from '../Service/shelter.service';
import { Router } from '@angular/router';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Pet } from '../Objects/Pet';
import { AdoptionService } from '../Service/adoption.service';
import { SharedService } from '../Service/shared.service';
import { Shelter } from '../Objects/Shelter';
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
    private adoptionService: AdoptionService,
    private sharedService: SharedService
  ) {}

  ngOnInit(): void {
    this.petService
      .getAllPetsInShelter(this.currentShelter.shelterId)
      .subscribe(
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
  description: any = '';
  index: any = 0;
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

  close() {
    $('#exampleModalCenter').modal('hide');
    $('#notify').modal('hide');
  }
  set_index(i: any) {
    this.index = i;
  }
  fill_application() {
    let formdata = new FormData();
    formdata.append('adopterid', '10');
    formdata.append('petid', this.pets[this.index].petId);
    formdata.append('description', this.description);
    this.adoptionService.make_adoption_request(formdata).subscribe((res) => {
      if (res == null) {
        alert('You have already made an adoption request for this pet');
      }
      console.log(res);
    });
  }
}
