import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificacoesdetailsComponent } from './notificacoesdetails.component';

describe('NotificacoesdetailsComponent', () => {
  let component: NotificacoesdetailsComponent;
  let fixture: ComponentFixture<NotificacoesdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotificacoesdetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotificacoesdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
