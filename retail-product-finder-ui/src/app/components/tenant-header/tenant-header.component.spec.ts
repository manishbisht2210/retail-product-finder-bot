import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TenantHeaderComponent} from './tenant-header.component';
import {RouterTestingModule} from '@angular/router/testing';
import {HttpClientModule} from '@angular/common/http';

describe('TenantHeaderComponent', () => {
  let component: TenantHeaderComponent;
  let fixture: ComponentFixture<TenantHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TenantHeaderComponent],
      imports: [RouterTestingModule, HttpClientModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TenantHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
