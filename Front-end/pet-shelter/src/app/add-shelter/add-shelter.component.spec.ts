import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddShelterComponent } from './add-shelter.component';

describe('AddShelterComponent', () => {
  let component: AddShelterComponent;
  let fixture: ComponentFixture<AddShelterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddShelterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddShelterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
