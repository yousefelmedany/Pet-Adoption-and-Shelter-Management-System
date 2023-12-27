import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StaffPageComponent } from './staff-page/staff-page.component';
import { ManageAdoptionComponent } from './manage-adoption/manage-adoption.component';
import { ManagepetsComponent } from './managepets/managepets.component';
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
