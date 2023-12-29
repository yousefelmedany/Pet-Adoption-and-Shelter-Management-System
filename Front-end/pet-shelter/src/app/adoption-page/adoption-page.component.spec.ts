import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdoptionPageComponent } from './adoption-page.component';

describe('AdoptionPageComponent', () => {
  let component: AdoptionPageComponent;
  let fixture: ComponentFixture<AdoptionPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdoptionPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdoptionPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
