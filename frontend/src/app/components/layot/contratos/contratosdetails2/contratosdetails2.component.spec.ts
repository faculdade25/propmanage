import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Contratosdetails2Component } from './contratosdetails2.component';

describe('Contratosdetails2Component', () => {
  let component: Contratosdetails2Component;
  let fixture: ComponentFixture<Contratosdetails2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Contratosdetails2Component]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Contratosdetails2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
