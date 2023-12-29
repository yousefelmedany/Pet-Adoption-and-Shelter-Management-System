import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StaffPageComponent } from './staff-page/staff-page.component';
import { ManageAdoptionComponent } from './manage-adoption/manage-adoption.component';
import { ManagepetsComponent } from './managepets/managepets.component';
import { SiginUpComponent } from './sigin-up/sigin-up.component';
import { SiginInComponent } from './sigin-in/sigin-in.component';
import { StaffProfileComponent } from './staff-profile/staff-profile.component';
import { UserpageComponent } from './userpage/userpage.component';
import { AdoptionPageComponent } from './adoption-page/adoption-page.component';
import { DocumentPageComponent } from './document-page/document-page.component';
import { ApplicationComponent } from './application/application.component';
import { ChooseShelterComponent } from './choose-shelter/choose-shelter.component';
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
      },
      {
        path: 'documentpage',
        component: DocumentPageComponent
      },
    ]
  },
  {
    path:'userpage',
    component: UserpageComponent,
    children: [
      {
        path: 'adoptionpage',
        component: AdoptionPageComponent
      },
      {
        path: 'application',
        component: ApplicationComponent
      },
      {
        path: 'chooseshelter',
        component: ChooseShelterComponent
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
