import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContratosdetailsComponent } from './contratosdetails.component';

describe('ContratosdetailsComponent', () => {
  let component: ContratosdetailsComponent;
  let fixture: ComponentFixture<ContratosdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContratosdetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContratosdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
