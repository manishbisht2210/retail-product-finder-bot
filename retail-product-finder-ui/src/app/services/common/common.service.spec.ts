import {TestBed} from '@angular/core/testing';

import {CommonService} from './common.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

describe('CommonService', () => {
  let service: CommonService;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CommonService]
    });
    service = TestBed.get(CommonService);
    httpMock = TestBed.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return allCommonData', () => {
    const dummyAllCommonData = {
      'tenantNames': [
        'Shri Guru Gobind Singhji Institute of Engineering and Technology',
        'Pune Institute of Computer Technology'
      ],
      'years': [
        'FIRST',
        'SECOND'
      ],
      'degrees': [
        'BTECH',
        'BE',
        'BCS',
        'MCS',
        'MTECH',
        'ME',
        'PHD'
      ]
    };

    service.getAllCommonData().subscribe(allCommonData => {
      expect(allCommonData).toEqual(dummyAllCommonData);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/getAllCommonData`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyAllCommonData);
  });
});
