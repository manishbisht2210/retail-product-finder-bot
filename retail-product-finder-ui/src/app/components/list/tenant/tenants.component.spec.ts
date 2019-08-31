import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TenantsComponent} from './tenants.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {TruncateDatePipe} from '../../../pipes/truncate-date-pipe';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {HttpClientModule} from '@angular/common/http';
import {RouterTestingModule} from '@angular/router/testing';

describe('TenantsComponent', () => {
  let component: TenantsComponent;
  let fixture: ComponentFixture<TenantsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TenantsComponent, TruncateDatePipe],
      schemas: [NO_ERRORS_SCHEMA],
      imports: [FormsModule, BsDropdownModule.forRoot(), HttpClientModule, RouterTestingModule],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TenantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
