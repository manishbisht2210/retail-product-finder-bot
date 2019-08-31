import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TenantUpdateComponent} from './tenant-update.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterTestingModule} from '@angular/router/testing';
import {HttpClientModule} from '@angular/common/http';

describe('TenantUpdateComponent', () => {
  let component: TenantUpdateComponent;
  let fixture: ComponentFixture<TenantUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TenantUpdateComponent],
      imports: [FormsModule, ReactiveFormsModule, RouterTestingModule, HttpClientModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TenantUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
