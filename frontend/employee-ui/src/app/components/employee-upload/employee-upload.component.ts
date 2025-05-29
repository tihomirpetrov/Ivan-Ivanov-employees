import {Component} from '@angular/core';
import {ProjectWorkResult} from "./projectWorkResult";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-employee-upload',
  templateUrl: './employee-upload.component.html',
  styleUrls: ['./employee-upload.component.scss']
})
export class EmployeeUploadComponent {
  selectedFile: File | null = null;
  results: ProjectWorkResult[] = [];
  errorMessage: string | null = null;
  isLoading = false;

  constructor(private http: HttpClient) {
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.errorMessage = null;

    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];

      const allowedTypes = ['text/csv', 'application/vnd.ms-excel'];
      if (!allowedTypes.includes(this.selectedFile.type)) {
        this.errorMessage = 'Only CSV files are allowed.';
        this.selectedFile = null;
        return;
      }

      if (this.selectedFile.size > 5 * 1024 * 1024) {
        this.errorMessage = 'File size must be less than 5MB.';
        this.selectedFile = null;
        return;
      }

      console.log('Selected file:', this.selectedFile.name);
    }
  }

  onUpload(event: Event): void {
    event.preventDefault();
    this.errorMessage = null;

    if (!this.selectedFile) {
      this.errorMessage = 'Please select a file before uploading.';
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.isLoading = true;

    this.http.post<ProjectWorkResult[]>('http://localhost:8080/api/employees/upload', formData, {observe: 'response'})
      .subscribe({
        next: (response) => {
          if (response.body) {
            this.results = response.body;
            this.errorMessage = null;
          } else {
            this.results = [];
            this.errorMessage = 'No data received from server.';
          }
          this.isLoading = false;
        },
        error: (error) => {
          console.error('Upload failed with error:', error);
          this.errorMessage = 'Upload failed, please try again.';
          this.results = [];
          this.isLoading = false;
        }
      });
  }
}
