import { Component, OnInit } from '@angular/core';
import { PetService } from '../Service/pet.service';
import { ShelterService } from '../Service/shelter.service';
import { Router } from '@angular/router';
import { Pet } from '../Objects/Pet';
@Component({
  selector: 'app-managepets',
  templateUrl: './managepets.component.html',
  styleUrls: ['./managepets.component.css']
})
export class ManagepetsComponent implements OnInit {

  constructor(
    private petService: PetService,
    private shelterService: ShelterService,
    private router: Router
  ) {}
pets: Pet[] = [];
  ngOnInit(): void {
    this.petService.getAllPetsInShelter(5).subscribe(
      (data) => {
        this.pets = data;
        console.log(this.pets);
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
