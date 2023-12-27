import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagepetsComponent } from './managepets.component';

describe('ManagepetsComponent', () => {
  let component: ManagepetsComponent;
  let fixture: ComponentFixture<ManagepetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagepetsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagepetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
