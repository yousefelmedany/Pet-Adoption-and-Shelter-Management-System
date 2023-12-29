import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../Service/auth.service';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-sigin-up',
  templateUrl: './sigin-up.component.html',
  styleUrls: ['./sigin-up.component.css']
})
export class SiginUpComponent {
  roles = ['ADOPTER', 'STAFF', 'MANAGER']; // Add more roles as needed

  selectedRole: string = '';
  loading=false
  signupForm: FormGroup;

  constructor(private fb: FormBuilder,private service:AuthService,private router:Router,private spinner: NgxSpinnerService) {
    this.signupForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      address:['',Validators.required,],
      phone:['',Validators.required],
      birthdate:[''],
      shelter:[''],
    });
  }

  onChangeRole(role: string) {
    this.selectedRole = role;
    this.resetForm();
  }

  resetForm() {
    // Reset the common fields
    this.signupForm.reset({
      username: '',
      email: '',
      password: '',
      address:'',
      phone:'',
      birthdate:'',
      shelter:''
    });
  }


  onSubmit() {
    const formData = this.signupForm.value;
    this.spinner.show()
    this.loading=true
    const userInfo = new UserInfo(
      formData.username,
      formData.email,
      formData.password,
      formData.phone,
      formData.address,
      formData.birthdate,
      this.selectedRole,
      formData.shelter
    );
    this.service.register(userInfo).subscribe(
      {
        next: (response) => {
          console.log(response);
          this.spinner.hide()
          this.loading=false
          this.router.navigate(['/signin']);
        },
        error: (err) => {
          this.spinner.hide()
          this.loading=false
          console.log(err);
        },
      }
    );
    console.log(`Role: ${this.selectedRole}`, formData);
  }
}

class UserInfo {
  name: string
  email: string
  password:string
  phone:string
  address:string
  birthDate:Date
  role:string
  shelterId:number
  constructor(name:string,email:string,password:string,phone:string,address:string,
    birthDate:Date,role:string,shelterId:number){
    this.name=name;
    this.email=email;
    this.password=password;
    this.phone=phone;
    this.address=address;
    this.birthDate=birthDate;
    this.role=role;
    this.shelterId=shelterId;
  }
}