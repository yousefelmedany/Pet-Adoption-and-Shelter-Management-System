import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SiginUpComponent } from './sigin-up.component';

describe('SiginUpComponent', () => {
  let component: SiginUpComponent;
  let fixture: ComponentFixture<SiginUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SiginUpComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SiginUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
