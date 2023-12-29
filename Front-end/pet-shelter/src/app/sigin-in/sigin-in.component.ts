import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../Service/auth.service';
import { Router } from '@angular/router';
import { StorageService } from '../Service/storage.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-sigin-in',
  templateUrl: './sigin-in.component.html',
  styleUrls: ['./sigin-in.component.css']
})
export class SiginInComponent {

  signInForm: FormGroup;
  loading=false

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router,private storage:StorageService,private spinner: NgxSpinnerService) {
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
          console.log('Response', response);
          this.storage.saveUser(response)
          this.spinner.hide()
          this.loading=false
        },
        error: (err) => {
          console.warn('Error', err);
          this.spinner.hide()
          this.loading=false
        }
      }
    )
  }

}
