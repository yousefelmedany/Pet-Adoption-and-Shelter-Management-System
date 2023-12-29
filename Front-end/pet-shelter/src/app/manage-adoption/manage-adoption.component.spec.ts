import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAdoptionComponent } from './manage-adoption.component';

describe('ManageAdoptionComponent', () => {
  let component: ManageAdoptionComponent;
  let fixture: ComponentFixture<ManageAdoptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ManageAdoptionComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ManageAdoptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
