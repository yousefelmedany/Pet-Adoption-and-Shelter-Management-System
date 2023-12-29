import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../Service/auth.service';
import { Router } from '@angular/router';
import { StorageService } from '../Service/storage.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { AdoptionService } from '../Service/adoption.service';
import { Adopter } from '../Objects/Adopter';
@Component({
  selector: 'app-sigin-in',
  templateUrl: './sigin-in.component.html',
  styleUrls: ['./sigin-in.component.css']
})
export class SiginInComponent {

  signInForm: FormGroup;
  loading=false

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router,private storage:StorageService,private spinner: NgxSpinnerService,private adoptionService:AdoptionService) {
    this.signInForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  onSubmit() {
    this.spinner.show()
    this.loading=true
    const formData = this.signInForm.value;
    this.authService.login({login:formData.email,password:formData.password}).subscribe(
      {
        next: (response) => {
          if(response.role[0]=='ADOPTER'){
           this.adoptionService.getAdopterById(response.personId).subscribe(res=>{
              let adopter:Adopter=res;
              this.storage.saveAdopter(adopter);
              this.router.navigate(['/userpage']);
           });
          }
          console.log('Response', response);
          this.storage.saveUser(response)
          this.spinner.hide()
          this.loading=false
          this.routeToPage(response.role[0])
        },
        error: (err) => {
          console.warn('Error', err);
          this.spinner.hide()
          this.loading=false
        }
      }
    )
  }
  routeToPage(role:string){
    if(role==="ADOPTER"){
      this.router.navigate(['/userpage']);
    }
    else{
      
      this.router.navigate(['/staffpage']);
    }
  }


}
