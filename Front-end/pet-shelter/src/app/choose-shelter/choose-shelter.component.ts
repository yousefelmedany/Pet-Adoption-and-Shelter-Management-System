import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShelterService } from '../Service/shelter.service';
import { Shelter } from '../Objects/Shelter';
import { SharedService } from '../Service/shared.service';
@Component({
  selector: 'app-choose-shelter',
  templateUrl: './choose-shelter.component.html',
  styleUrls: ['./choose-shelter.component.css']
})
export class ChooseShelterComponent implements OnInit {

  constructor(
    private router: Router,
    private shelterService: ShelterService,
    private sharedService:SharedService
  ) { }
  shelters:Shelter[]=[];

  ngOnInit(): void {
    this.shelterService.getAllShelters().subscribe(res=>{
      this.shelters=res;
      console.log(this.shelters);
    });
  }
  goToShelter(i:number){
    this.sharedService.setChoosedShelter(this.shelters[i]);
    this.router.navigate(['/userpage/adoptionpage']);
  }

}
