import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ShelterService } from '../Service/shelter.service';
import { StorageService } from '../Service/storage.service';
import { Shelter } from '../Objects/Shelter';
import { Staff } from '../Objects/Staff';

@Component({
  selector: 'app-add-shelter',
  templateUrl: './add-shelter.component.html',
  styleUrls: ['./add-shelter.component.css']
})
export class AddShelterComponent implements OnInit {

  form: FormGroup;
  loading: boolean = false;
  Shelter:Shelter=new Shelter()
  staff!:Staff
  hasShelter=false
  constructor(
    private fb: FormBuilder,
    private storage: StorageService,
    private service: ShelterService,
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      phone: ['', Validators.required],
    });
    this.staff=this.storage.getStaff()
    console.log("here",this.staff)
    if(this.staff.shelter){
       this.hasShelter=true
    }

  }

  ngOnInit(): void {
  }
  onSubmit(): void {
    this.loading = true;
    this.service.saveShelter(this.Shelter,this.staff.staffId).subscribe((data) => {
      if (data != null) {
        this.staff.shelter=data
        this.storage.saveStaff(this.staff)
        this.loading = false;
        alert("Shelter Added Successfully");
        this.Shelter.shelterAddress=''
        this.Shelter.shelterName=''
        this.Shelter.shelterPhone=''
        window.location.reload();
        
      } else {
        this.loading = false;
        this.Shelter.shelterAddress=''
        this.Shelter.shelterName=''
        this.Shelter.shelterPhone=''
        alert("Error Occured");

      }
    });
  }

}
