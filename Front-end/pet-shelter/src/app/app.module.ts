import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StaffPageComponent } from './staff-page/staff-page.component';
import { ManagepetsComponent } from './managepets/managepets.component';
import { ManageAdoptionComponent } from './manage-adoption/manage-adoption.component';
import { FormsModule } from '@angular/forms';
import { DocumentPageComponent } from './document-page/document-page.component';
import { UserpageComponent } from './userpage/userpage.component';
import { AdoptionPageComponent } from './adoption-page/adoption-page.component';
import { ApplicationComponent } from './application/application.component';
import { ChooseShelterComponent } from './choose-shelter/choose-shelter.component';
@NgModule({
  declarations: [
    AppComponent,
    StaffPageComponent,
    ManagepetsComponent,
    ManageAdoptionComponent,
    UserpageComponent,
    AdoptionPageComponent,
    DocumentPageComponent,
    ApplicationComponent,
    ChooseShelterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
