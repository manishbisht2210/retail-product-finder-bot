import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TenantAddComponent} from './tenant-add.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RouterTestingModule} from '@angular/router/testing';

describe('TenantAddComponent', () => {
  let component: TenantAddComponent;
  let fixture: ComponentFixture<TenantAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TenantAddComponent],
      imports: [ReactiveFormsModule, HttpClientModule, RouterTestingModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TenantAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
