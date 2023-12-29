import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SiginInComponent } from './sigin-in.component';

describe('SiginInComponent', () => {
  let component: SiginInComponent;
  let fixture: ComponentFixture<SiginInComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SiginInComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SiginInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
