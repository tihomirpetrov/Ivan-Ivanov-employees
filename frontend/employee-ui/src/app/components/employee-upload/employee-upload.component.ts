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

  constructor(private http: HttpClient) {
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      console.log('Selected file:', this.selectedFile.name);
    }
  }

  onUpload(event: Event): void {
    event.preventDefault();
    if (!this.selectedFile) return;

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    console.log('Uploading file:', this.selectedFile?.name);

    this.http.post<ProjectWorkResult[]>('http://localhost:8080/api/employees/upload', formData, {observe: 'response'})
      .subscribe({
        next: (response) => {
          console.log('HTTP status:', response.status);
          console.log('Response body:', response.body);

          if (response.body) {
            this.results = response.body;
          } else {
            console.warn('Empty response body');
            this.results = [];
          }
        },
        error: (error) => {
          console.error('Upload failed with error:', error);
          this.results = [];
        }
      });
  }
}
