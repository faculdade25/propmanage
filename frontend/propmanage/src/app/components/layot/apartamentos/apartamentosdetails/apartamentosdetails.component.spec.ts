import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApartamentosdetailsComponent } from './apartamentosdetails.component';

describe('ApartamentosdetailsComponent', () => {
  let component: ApartamentosdetailsComponent;
  let fixture: ComponentFixture<ApartamentosdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApartamentosdetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApartamentosdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
