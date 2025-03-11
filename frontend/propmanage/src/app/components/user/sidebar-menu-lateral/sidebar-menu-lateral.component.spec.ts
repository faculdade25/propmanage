import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarMenuLateralComponent } from './sidebar-menu-lateral.component';

describe('SidebarMenuLateralComponent', () => {
  let component: SidebarMenuLateralComponent;
  let fixture: ComponentFixture<SidebarMenuLateralComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SidebarMenuLateralComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SidebarMenuLateralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
