import { TestBed } from '@angular/core/testing';

import { GuardAdminService } from './guard-admin.service';

describe('GuardAdminService', () => {
  let service: GuardAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuardAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
