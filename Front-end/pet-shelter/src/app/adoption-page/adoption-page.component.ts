import { Component, OnInit } from '@angular/core';
import { PetService } from '../Service/pet.service';
import { ShelterService } from '../Service/shelter.service';
import { Router } from '@angular/router';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Pet } from '../Objects/Pet';
import { SharedService } from '../Service/shared.service';
import { Shelter } from '../Objects/Shelter';
import { AdoptionService } from '../Service/adoption.service';
import { StorageService } from '../Service/storage.service';
import { Adopter } from '../Objects/Adopter';
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
    private sharedService: SharedService,
    private storageService: StorageService
  ) {}

  ngOnInit(): void {
    this.petService
      .getAllPetsInShelter(this.currentShelter.shelterId)
      .subscribe(
        (data) => {
          this.pets = data;
          this.filteredPets = data;
          for (let i = 0; i < this.pets.length; i++) {
            this.images.push(this.convertToImage(this.pets[i].image));
          }
          this.selectedValue = 'Training';
          console.log(this.pets);
        },
        (error) => {
          console.log(error);
        }
      );
  }

  pets: Pet[] = [];
  filteredPets: Pet[] = [];
  spinner_flag: boolean = false;
  newPet: Pet = new Pet();
  selectedFile!: File;
  images: any[] = [];
  description: any = '';
  index: any = 0;
  selectedValue: string = 'Training';
  currentShelter: Shelter = this.sharedService.getChoosedShelter();
  currentAdopter: Adopter = this.storageService.getAdopter();
  filterValue: string = '';
  filter: string = 'filter';

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
    this.spinner_flag = true;
    let formdata = new FormData();
    formdata.append('adopterid', this.currentAdopter.adopterId);
    formdata.append('petid', this.filteredPets[this.index].petId);
    formdata.append('description', this.description);
    this.adoptionService.make_adoption_request(formdata).subscribe((res) => {
      if (res == null) {
        alert('You have already made an adoption request for this pet');
      }
      this.spinner_flag = false;
      $('#exampleModalCenter').modal('hide');
      $('#notify').modal('show');
      console.log(res);
    });
  }
  applyFilter() {
    if (this.filterValue == '') {
      this.filter = 'filter';
    } else {
      this.filter = '';
    }
    if (this.selectedValue == 'Training') {
      this.filteredPets = this.pets.filter((pet) =>
        pet.training.toLowerCase().includes(this.filterValue.toLowerCase())
      );
      this.images=this.filteredPets.map(pet=>this.convertToImage(pet.image));
    } else if (this.selectedValue == 'Vaccination') {
      this.filteredPets = this.pets.filter((pet) =>
        pet.vaccination.toLowerCase().includes(this.filterValue.toLowerCase())
      );
      this.images=this.filteredPets.map(pet=>this.convertToImage(pet.image));
    } else if (this.selectedValue == 'SpayNeuter') {
      this.filteredPets = this.pets.filter((pet) =>
        pet.spayNeuter.toLowerCase().includes(this.filterValue.toLowerCase())
      );
      this.images=this.filteredPets.map(pet=>this.convertToImage(pet.image));
    }
  }
}
