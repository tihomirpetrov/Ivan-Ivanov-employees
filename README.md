# Employee Overlap Calculator & EmployeeUi

## Preview
![Screenshot of employee overlap UI](https://github.com/user-attachments/assets/d0efad4c-2e35-4ccc-a376-b5fb755234c7)

## Description

This project calculates how many days pairs of employees have worked together on common projects based on a CSV upload.

- Supports CSV input where `DateTo` can be `NULL` (treated as today).
- Calculates overlapping days per project between employee pairs.
- Returns results listing Employee ID #1, Employee ID #2, Project ID, and days worked together.
- Includes a frontend UI for CSV file upload and displaying results in a data grid.

---

## Technologies Used

- Backend: Java Spring Boot (Java 17)
- Frontend: Angular (version 16.2.16)

---

## Docker Compose Setup

1. Prerequisites
   Ensure you have installed:
   - [Docker](https://docs.docker.com/get-docker/)
   - [Docker Compose](https://docs.docker.com/compose/install/)

2. Clone the repository
   ```bash
   git clone https://github.com/tihomirpetrov/Ivan-Ivanov-employees.git
   cd Ivan-Ivanov-employees

3. Build and start containers
   ```bash
   docker-compose up --build -d

4. Access the application
	
	Frontend: http://localhost:4200
	
	Backend API: http://localhost:8080



## Backend - How to Run (Manual)

1. Clone the repository:

   ```bash
   git clone https://github.com/tihomirpetrov/Ivan-Ivanov-employees.git
   cd Ivan-Ivanov-employees

2. Build and run the Spring Boot application:
   ./mvnw spring-boot:run

3. The backend will be available at:
   http://localhost:8080

4. API endpoint for CSV upload:
   POST http://localhost:8080/api/employees/upload
Accepts multipart form-data with the file parameter.
Returns JSON array with results.

## Frontend - How to Run
1. Navigate to the frontend folder: 
   cd frontend
2. Install dependencies:
   npm install
3. Run the development server:
   ng serve
4. Open your browser at:
   http://localhost:4200
5. Use the UI to select and upload a CSV file. The results will be displayed in the table.

Angular CLI Commands

    Development server:
    Run ng serve for a live reload dev server at http://localhost:4200/.

    Generate components/services/etc:
    Run ng generate component component-name.

    Build project:
    Run ng build to build the production artifacts into the dist/ folder.

    Run unit tests:
    Run ng test to execute tests with Karma.

    Run end-to-end tests:
    Run ng e2e to execute end-to-end tests. (Requires additional setup.)

    Help:
    Run ng help or visit the Angular CLI documentation.

Usage Instructions

    Prepare a CSV file with employee project data, ensuring the DateTo field can be empty or NULL to indicate ongoing projects.

    Start backend and frontend servers.

    Upload the CSV file via the frontend UI.

    View pairs of employees who worked together, their common projects, and total days worked together.

Possible Improvements

    Support multiple date formats during CSV parsing.

    Enhance frontend with loading spinners and error handling.

    Validate CSV file format before upload.

    Persist results in a database for future queries.

    Add comprehensive backend and frontend tests.

License

This project is licensed under the MIT License.
