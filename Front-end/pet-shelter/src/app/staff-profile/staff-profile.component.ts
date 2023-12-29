import { Component, OnInit } from '@angular/core';
import { SafeResourceUrl } from '@angular/platform-browser';
import { AuthService } from '../Service/auth.service';
import { StorageService } from '../Service/storage.service';
import { Staff } from '../Objects/Staff';

@Component({
  selector: 'app-staff-profile',
  templateUrl: './staff-profile.component.html',
  styleUrls: ['./staff-profile.component.css']
})
export class StaffProfileComponent implements OnInit {

  staff!: Staff;
  cvBlobUrl?: SafeResourceUrl;
  flag: boolean = true;
  emptyFields: string[] = [];
  selectedImage: any;
  hasPhoto = false;
  isEdit = false;
  originalstaff!: Staff;
  imageUrl: any;
  activeTab: string = 'about';
  defaultImageUrl: string = '../../assets/nophoto.png';
  spinner_flag: boolean = false;
  user!:{name:string,role:any,personId:number,email:string}
  constructor(private service:AuthService,private storage:StorageService) { 
    this.user = this.storage.getUser();
    console.log(this.user)
    this.service.getCurrentUser().subscribe(
      {
        next: (response) => {
          this.staff = response;
          this.staff.email=this.user.email
          this.staff.role=this.user.role[0]
          console.log('Response', response);
          this.originalstaff = { ...this.staff };
        },
        error: (err) => {
          console.warn('Error', err);
        }
      }
    )

  }
  ngOnInit(): void {
 
    
  }
  toggleEditMode() {
    this.isEdit = true;
  }
  saveChanges() {
    // Check for changes
    this.spinner_flag = true;
    const hasChanges = !this.areObjectsEqual(this.staff, this.originalstaff);

    if (hasChanges) {
      // Handle empty fields if needed
      this.emptyFields = this.getEmptyFields(this.staff);
      console.log('saving');
      console.log(this.staff);

      // this.staffservice.updatestaff(this.staff).subscribe({
      //   next: (response) => {
      //     console.log('staff updated successfully:', response);
      //     this.originalstaff = { ...this.staff };
      //     this.spinner_flag = false;

      //     $('#exampleModalCenter').modal('hide');
      //     $('#notify').modal('show');
      //   },
      //   error: (error) => {
      //     console.error('Error updating coach:', error);
      //     // window.location.reload()
      //   },
      // });
      // } else {
      // alert('Empty fields:'+ emptyFields);
      this.isEdit = false;
      // Handle empty fields as needed (e.g., display a message to the user)
      // }
    } else {
      console.log('No changes detected');
      this.spinner_flag = false;
      // ($('#exampleModalCenter')as any).modal('hide');
      // ($('#notify')as any).modal('show');
      this.isEdit = false;
    }
  }
  close() {
    // ($('#exampleModalCenter') as any).modal('hide');
    // ($('#notify') as any).modal('hide');
  }

  areObjectsEqual(obj1: any, obj2: any): boolean {
    // Implement deep object comparison logic as needed
    // Example: Compare specific fields for equality
    return JSON.stringify(obj1) === JSON.stringify(obj2);
  }

  getEmptyFields(obj: any): string[] {
    const emptyFields: string[] = [];

    for (const key of Object.keys(obj)) {
      if (!obj[key]) {
        emptyFields.push(key);
      }
    }

    return emptyFields;
  }



//   changePassword() {
//     const dialogRef = this.dialog.open(ChangePasswordDialogComponent, {
//       width: '400px',
//     });

//     dialogRef.afterClosed().subscribe(result => {
//       if (result) {
//         this.openConfirmDialog("Are you sure you want to update your password?", result)
//       }
//     });

//   }
//   openConfirmDialog(data: any, pasword: any): void {
//     this.loadingService.setLoading(true);
//     const dialogRef = this.dialog.open(ConfirmDialogComponent, {
//       width: '300px',
//       data: {
//         title: "Confirm Change Password",
//         body: data,
//       }
//     });

//     dialogRef.afterClosed().subscribe(result => {
//       if (result == "confirm") {
//         this.authService.updatePassword(this.coach.email, 'coach', pasword.currentPassword, pasword.newPassword).subscribe((res) => {
//           if (res.success) {
//             alert(res.message)
//           }
//           else {
//             alert(res.message)
//           }
//         },
//         )
//         this.loadingService.setLoading(false);
//       }
//     });
//   }
// }

}
