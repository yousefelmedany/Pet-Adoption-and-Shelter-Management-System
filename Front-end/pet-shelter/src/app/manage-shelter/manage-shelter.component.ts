import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { Shelter } from '../Objects/Shelter';
import { Staff } from '../Objects/Staff';
import { StorageService } from '../Service/storage.service';
import { ShelterService } from '../Service/shelter.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-manage-shelter',
  templateUrl: './manage-shelter.component.html',
  styleUrls: ['./manage-shelter.component.css']
})
export class ManageShelterComponent implements OnInit {
  staff!:Staff
  hasShelter=false
  loading= false;
  @Input() Shelter!: Shelter;
  shelterForm: FormGroup;
  isEditing: boolean = false;
  constructor(
    private storage: StorageService,
    private service: ShelterService,
    private fb: FormBuilder
  ) { 
    this.shelterForm = this.fb.group({
      shelterName: ['', [Validators.required, Validators.minLength(3)]],
      shelterPhone: ['', Validators.required],
      shelterAddress: ['', Validators.required],
    });
    this.staff=this.storage.getStaff()
    if(this.staff.shelter){
       this.hasShelter=true
       this.Shelter=this.staff.shelter
       console.log(this.Shelter)
    }
  }
  ngOnInit(): void {
    this.shelterForm.patchValue({
      shelterName: this.Shelter.shelterName,
      shelterPhone: this.Shelter.shelterPhone,
      shelterAddress: this.Shelter.shelterAddress,
    });
  }


  ngOnChanges(changes: SimpleChanges): void {
    if (changes['Shelter'] && changes['Shelter'].currentValue) {
      this.shelterForm.patchValue({
        shelterName: this.Shelter.shelterName,
        shelterPhone: this.Shelter.shelterPhone,
        shelterAddress: this.Shelter.shelterAddress,
      });
    }
  }

  toggleEdit(): void {
    if (this.isEditing) {
      const currentFormValues = this.shelterForm.value;
      const initialFormValues = {
        shelterName: this.Shelter.shelterName,
        shelterPhone: this.Shelter.shelterPhone,
        shelterAddress: this.Shelter.shelterAddress,
      };
  
      // Check if there are any changes in form values
      const hasChanges = !this.isEqual(currentFormValues, initialFormValues);
  
      if (hasChanges) {
        this.loading = true;
        console.log('Save changes:', currentFormValues);
        const dialogRef = confirm("Are you sure to update the Shelter data?");
      if (dialogRef){
        //copy fields in the Shelter object
        this.Shelter.shelterAddress=this.shelterForm.value.shelterAddress
        this.Shelter.shelterName=this.shelterForm.value.shelterName
        this.Shelter.shelterPhone=this.shelterForm.value.shelterPhone
        this.service.editShelter(this.Shelter).subscribe((data) => {
          if (data != null) {
            this.loading = false;
            alert("Shelter Updated Successfully");
            this.Shelter.shelterAddress=this.shelterForm.value.shelterAddress
            this.Shelter.shelterName=this.shelterForm.value.shelterName
            this.Shelter.shelterPhone=this.shelterForm.value.shelterPhone
            this.staff.shelter=this.Shelter
            this.storage.saveStaff(this.staff)
          } else
          {
            this.loading = false;
            alert("Error Occured");
          }
        });
      }
        
      } else {
        console.log('No changes detected.');
      }
  
      // Rest of your logic
    }
  
    this.isEditing = !this.isEditing;
    this.toggleFormReadOnly();
  }
  
  private isEqual(obj1: any, obj2: any): boolean {
    return JSON.stringify(obj1) === JSON.stringify(obj2);
  }
  
  deleteShelter(): void {
    console.log('Delete shelter:', this.Shelter);
    this.loading=true
    const dialogRef = confirm("Are you sure to delete the Shelter?");
    if (dialogRef) {
      this.service.deleteShelter(this.Shelter.shelterId).subscribe((data) => {  
          alert("Shelter Deleted Successfully");
          this.Shelter.shelterAddress=''
          this.Shelter.shelterName=''
          this.Shelter.shelterPhone=''
          this.staff.shelter=null
          this.hasShelter=false
          this.loading=false
          this.storage.saveStaff(this.staff)
      });
    }
    
  }

  private toggleFormReadOnly(): void {
    const controls = this.shelterForm.controls;
    Object.keys(controls).forEach((key) => {
      const control = controls[key];
      if (this.isEditing) {
        control.enable();
      } else {
        control.disable();
      }
    });
  }

}
