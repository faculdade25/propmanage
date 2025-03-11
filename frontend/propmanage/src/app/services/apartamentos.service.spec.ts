import { TestBed } from '@angular/core/testing';

import { ApartamentosService } from './apartamentos.service';

describe('ApartamentosService', () => {
  let service: ApartamentosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApartamentosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
