import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TenantDetailComponent} from './tenant-detail.component';
import {HttpClientModule} from '@angular/common/http';
import {TruncateDatePipe} from '../../../pipes/truncate-date-pipe';
import {RouterTestingModule} from '@angular/router/testing';

describe('TenantDetailComponent', () => {
  let component: TenantDetailComponent;
  let fixture: ComponentFixture<TenantDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TenantDetailComponent, TruncateDatePipe],
      imports: [HttpClientModule, RouterTestingModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TenantDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
