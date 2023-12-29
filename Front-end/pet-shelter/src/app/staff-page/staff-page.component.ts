import { Component, OnInit } from '@angular/core';
import { StorageService } from '../Service/storage.service';
import { Router } from '@angular/router';
import { AuthService } from '../Service/auth.service';
import { Staff } from '../Objects/Staff';

@Component({
  selector: 'app-staff-page',
  templateUrl: './staff-page.component.html',
  styleUrls: ['./staff-page.component.css'],
})
export class StaffPageComponent implements OnInit {
  constructor(private storage: StorageService, private router: Router) {}
  user!:{name:string,role:any,personId:number,email:string}
  staff!: Staff;
  role!:String
  constructor(private storage:StorageService,private router:Router,private service:AuthService) {
    this.user=this.storage.getUser()
    this.role=this.user.role[0]
   }
  ngOnInit(): void {
    this.user = this.storage.getUser();
    console.log(this.user)
    this.service.getCurrentUser().subscribe(
      {
        next: (response) => {
          this.staff = response;
          this.staff.email=this.user.email
          this.staff.role=this.user.role[0]
          this.storage.saveStaff(this.staff)
        },
        error: (err) => {
          console.warn('Error', err);
        }
      }
    )
  }
  logout(){
    this.storage.clean()
    this.router.navigate(['/signin']);
  }
}
