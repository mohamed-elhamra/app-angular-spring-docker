import { TestBed } from '@angular/core/testing';

import { ZbiriService } from './zbiri.service';

describe('ZbiriService', () => {
  let service: ZbiriService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZbiriService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
