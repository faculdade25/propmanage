import { TestBed } from '@angular/core/testing';

import { ContratosService } from './contratos.service';

describe('ContratosService', () => {
  let service: ContratosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContratosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
