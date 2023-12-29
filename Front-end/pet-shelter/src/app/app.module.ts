import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StaffPageComponent } from './staff-page/staff-page.component';
import { ManagepetsComponent } from './managepets/managepets.component';
import { ManageAdoptionComponent } from './manage-adoption/manage-adoption.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { HttpRequestInterceptor } from './_helpers/http.interceptor';
import { SiginInComponent } from './sigin-in/sigin-in.component';
import { SiginUpComponent } from './sigin-up/sigin-up.component';
import { StorageService } from './Service/storage.service';
import { NgxSpinnerModule } from 'ngx-spinner';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StaffProfileComponent } from './staff-profile/staff-profile.component';
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
    SiginInComponent,
    SiginUpComponent,
    StaffProfileComponent,
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
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule, 
    NgxSpinnerModule,
  ],
  providers: [
    CookieService,
    StorageService,
    { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
