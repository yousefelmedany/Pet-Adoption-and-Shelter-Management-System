import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseShelterComponent } from './choose-shelter.component';

describe('ChooseShelterComponent', () => {
  let component: ChooseShelterComponent;
  let fixture: ComponentFixture<ChooseShelterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChooseShelterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChooseShelterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
