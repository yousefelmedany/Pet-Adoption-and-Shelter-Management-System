import { Component, OnInit } from '@angular/core';
import { AdoptionService } from '../Service/adoption.service';
import { Application } from '../Objects/Application';
import { Adopter } from '../Objects/Adopter';
import { StorageService } from '../Service/storage.service';
@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  constructor(
    private adoptionService: AdoptionService,
    private storageService: StorageService
  ) { }
  applications:Application[]=[];
  approvedApplications:Application[]=[];
  currentAdopter:Adopter=this.storageService.getAdopter();
  ngOnInit(): void {
    this.adoptionService.getApplicationsByAdopterId(this.currentAdopter.adopterId).subscribe(res=>{
      this.applications=res;
      for(let i=0;i<this.applications.length;i++){
        if(this.applications[i].status=="Approved"){
          this.approvedApplications.push(this.applications[i]);
        }
      }
      console.log(this.applications);
    });
  }

}
