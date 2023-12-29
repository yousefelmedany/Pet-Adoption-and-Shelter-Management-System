import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StaffPageComponent } from './staff-page/staff-page.component';
import { ManageAdoptionComponent } from './manage-adoption/manage-adoption.component';
import { ManagepetsComponent } from './managepets/managepets.component';
import { UserpageComponent } from './userpage/userpage.component';
import { AdoptionPageComponent } from './adoption-page/adoption-page.component';
export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
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
        path: 'manageadoption',
        component: ManageAdoptionComponent
      }
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
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
