import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StaffPageComponent } from './staff-page/staff-page.component';
import { ManageAdoptionComponent } from './manage-adoption/manage-adoption.component';
import { ManagepetsComponent } from './managepets/managepets.component';
import { SiginUpComponent } from './sigin-up/sigin-up.component';
import { SiginInComponent } from './sigin-in/sigin-in.component';
import { StaffProfileComponent } from './staff-profile/staff-profile.component';
export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path:'signup',
    component: SiginUpComponent
  },
  {
    path:'signin',
    component: SiginInComponent
  },
  {
    path:'staffpage',
    component: StaffPageComponent,
    children: [
      {
        path: 'managepets',
        component: ManagepetsComponent
      },
      {
        path: 'staffprofile',
        component: StaffProfileComponent
      },
      {
        path: 'manageadoption',
        component: ManageAdoptionComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
