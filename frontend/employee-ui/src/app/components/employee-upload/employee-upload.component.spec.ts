import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeUploadComponent } from './employee-upload.component';

describe('EmployeeUploadComponent', () => {
  let component: EmployeeUploadComponent;
  let fixture: ComponentFixture<EmployeeUploadComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmployeeUploadComponent]
    });
    fixture = TestBed.createComponent(EmployeeUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
