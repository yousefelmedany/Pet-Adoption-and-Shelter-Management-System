import { Component, OnInit } from '@angular/core';
import { StaffService } from '../Service/staff.service';
import { Application } from '../Objects/Application';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-manage-adoption',
  templateUrl: './manage-adoption.component.html',
  styleUrls: ['./manage-adoption.component.css']
})
export class ManageAdoptionComponent implements OnInit {

  Applications: Application[] = [];
  constructor(
    private staffservice:StaffService,
    private sanitizer: DomSanitizer
    ) { }
   images: any[] = [];
  
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
        for(let i=0;i<this.Applications.length;i++){
          this.images.push(this.convertToImage(this.Applications[i].pet.image));
        }
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
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
