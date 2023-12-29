import { Component, OnInit } from '@angular/core';
import { StaffService } from '../Service/staff.service';
import { Application } from '../Objects/Application';

@Component({
  selector: 'app-manage-adoption',
  templateUrl: './manage-adoption.component.html',
  styleUrls: ['./manage-adoption.component.css']
})
export class ManageAdoptionComponent implements OnInit {

  Applications: Application[] = [];
  constructor(private staffservice:StaffService) { }
  
  
  ngOnInit(): void {  
    this.staffservice.GetPendingRequests().subscribe(
      (data) => {
        this.Applications = data;
        console.log(this.Applications);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  Approve(i:any){
    this.staffservice.AcceptApplication(this.Applications[i].adopter.adopterId,this.Applications[i].pet.petId).subscribe(
      (data) => {
        this.Applications = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  Decline(i:any){
    this.staffservice.DeclineApplication(this.Applications[i].adopter.adopterId,this.Applications[i].pet.petId).subscribe(
      (data) => {
        console.log(data);
      this.Applications.splice(i,1);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
