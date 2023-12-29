import { Component, OnInit } from '@angular/core';
import { StorageService } from '../Service/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-staff-page',
  templateUrl: './staff-page.component.html',
  styleUrls: ['./staff-page.component.css']
})
export class StaffPageComponent implements OnInit {

  constructor(private storage:StorageService,private router:Router) { }

  ngOnInit(): void {
  }
  logout(){
    this.storage.clean()
    this.router.navigate(['/signin']);
    

  }

}
