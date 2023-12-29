import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageShelterComponent } from './manage-shelter.component';

describe('ManageShelterComponent', () => {
  let component: ManageShelterComponent;
  let fixture: ComponentFixture<ManageShelterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageShelterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageShelterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
