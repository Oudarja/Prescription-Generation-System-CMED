# Prescription-Generation-System-CMED

## Overview
The **Prescription Generation System** is a web-based application designed to streamline the management of patient prescriptions. It allows authorized users (assistants or admins) to create, edit, delete, and view prescriptions efficiently. The system also provides insights such as day-wise prescription counts and filtered prescription lists based on date ranges.

The project is built using **React.js** for frontend and **Spring Boot** (Java 17) for backend, with REST APIs and JWT-based authentication for secure access. Swagger is used for API documentation and testing.

---

## Features

### 1. Prescription Management
- **Create/Edit Prescription Form**
  - Add new prescriptions with patient details, diagnosis, medicines, prescription date, and next visit date.
  - Edit existing prescriptions using pre-filled forms.
- **Delete Prescriptions**
  - Remove a prescription after user confirmation.
- **List Prescriptions**
  - Display prescriptions in a styled table with proper borders and padded cells.

### 2. Date-Range Filtering
- Prescription lists can be filtered by selecting a specific date.
- By default, the system shows prescriptions from **the 1st of the current month to yesterday**.
- Ensures users only see relevant prescriptions within the selected date range.

### 3. Day-Wise Prescription Count
- Fetches day-wise counts of prescriptions from the API.
- Displays data in a table format showing the **date** and **number of prescriptions**.
- Handles empty responses gracefully.

### 4. API Integration & Swagger
- REST APIs used for all data operations.
- JWT-based authentication ensures secure access to endpoints.
- Swagger UI available for testing and documentation of APIs.

### 5. UI Enhancements
- **Date picker** with:
  - Upon successful login, the system automatically sets the default date to yesterday, ensuring that authenticated users initially view prescriptions from the 1st of the current month up to yesterday’s date.
  - Centered text and proper padding
  - Rounded borders for better appearance
- **Tables**
  - Clear black borders for all cells
  - Proper padding for readability
  - Styled headings centered
- **Actions**
  - Edit and Delete buttons for each prescription
---

## Tech Stack
- **Frontend:** React.js
- **Backend:** Spring Boot (Java 17)
- **Database:** H2 (for development)
- **Build Tools:** Maven
- **Others:** Axios (API calls), React Router (navigation), Swagger (API docs), JWT (authentication)
---

## Setup Instructions

### Backend
1. Clone the repository and navigate to the backend folder.
2. Ensure **Java 17** and **Maven** are installed.
3. Run the Spring Boot application:
```bash
mvn spring-boot:run
```
4. APIs will be available at http://localhost:8080.
5. 5.Swagger UI is available at http://localhost:8080/swagger-ui.html.

### Frontend
1. Navigate to the frontend folder.
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the React app:
   ```bash
   npm run start
   ```
4. App will be available at http://localhost:3000.

### API Endpoints (Overview)
- /auth/register -> Register

- /auth/login → JWT login
 
- /prescriptions/fetch-all-prescriptions → Get all prescriptions (not used)

- /prescriptions/fetch-all-by-month/{year}/{month}/{day} → Get prescriptions from 1st of month to selected day

- /prescriptions/fetch-one/{id} -> Get just one prescription of specific id

- /prescriptions/report/daywise → Get day-wise prescription counts

- /prescriptions/delete/{id} → Delete a prescription

- /prescriptions/create-prescription → Add a new prescription

### Project Folder Structure
 - #### Frontend (prescription-generation-frontend) :
``` bash
  prescription-generation-frontend/
│── node_modules/              # Dependencies
│── public/                    # Public assets (favicon, index.html, etc.)
│── src/                       # Source code
│   ├── Components/            # React components
│   │   ├── Consume.js
│   │   ├── LoginForm.js
│   │   ├── Navbar.js
│   │   ├── PrescriptionForm.js
│   │   ├── PrescriptionList.js
│   │   ├── PrescriptionPerDay.js
│   │   └── RegistrationForm.js
│   ├── services/              # API integration
│   │   └── api.js
│   ├── App.css
│   ├── App.js
│   ├── App.test.js
│   ├── index.css
│   ├── index.js
│   ├── logo.svg
│   ├── reportWebVitals.js
│   └── setupTests.js
│── package.json               # Dependencies & scripts
│── package-lock.json
│── README.md
```

- #### Backend (PrescriptionGenerationBackend):
``` bash
PrescriptionGenerationBackend/
│── .mvn/                      # Maven wrapper
│── data/
│   └── prescriptiondb.mv.db   # H2 database file
│── src/
│   └── main/
│       ├── java/com/example/PrescriptionGenerationBackend/
│       │   ├── Config/
│       │   │   ├── OpenApiConfig.java
│       │   │   └── SecurityConfig.java
│       │   ├── Controller/
│       │   │   ├── AuthController.java
│       │   │   └── PrescriptionController.java
│       │   ├── Dto/
│       │   │   ├── LoginRequest.java
│       │   │   └── PrescriptionRequest.java
│       │   ├── Model/
│       │   │   ├── Prescription.java
│       │   │   └── User.java
│       │   ├── Repository/
│       │   │   ├── PrescriptionRepository.java
│       │   │   └── UserRepository.java
│       │   ├── Security/
│       │   │   ├── JwtAuthenticationFilter.java
│       │   │   └── JwtUtil.java
│       │   ├── Services/
│       │   │   ├── PrescriptionService.java
│       │   │   └── UserService.java
│       │   └── PrescriptionGenerationApplication.java
│       └── resources/
│           ├── static/
│           ├── templates/
│           └── application.properties
│── pom.xml                     # Maven dependencies
│── mvnw / mvnw.cmd             # Maven wrapper scripts
│── HELP.md
│── .env
```
### File Descriptions: 
- #### Frontend:
  - Consume.js → Handles fetching and consuming prescription data from backend for a given api but it results in `no data found`.
  - LoginForm.js → Login form UI + API call for authentication (JWT-based).
  - Navbar.js → Navigation bar with links to different app sections.
  - PrescriptionForm.js → Form for creating/editing prescriptions (patient details, medicines, etc.).
  - PrescriptionList.js → Displays prescriptions in a styled table with edit/delete options.
  - PrescriptionPerDay.js → Shows day-wise prescription count report.
  - RegistrationForm.js → User registration form (admin/assistant accounts).
  - services/api.js → Centralized API calls using Axios (authentication, prescription CRUD).
  - App.js → Root React component, handles routing/navigation.
  - App.css / index.css → Styling files.
  - index.js → Entry point of React app, renders <App />.
- #### Backend :
 - Config
   - OpenApiConfig.java → Swagger/OpenAPI configuration for API documentation.
   - SecurityConfig.java → Spring Security + JWT authentication setup.
 - Controller
   - AuthController.java → Handles login & user authentication (JWT token generation).
   - PrescriptionController.java → CRUD APIs for prescriptions (create, Read, Delete, Update).
 - DTO (Data Transfer Objects)
   - LoginRequest.java → Request payload for user login.
   - PrescriptionRequest.java → Request payload for creating/editing prescriptions.
 - Model
   - Prescription.java → Entity class representing a prescription record.
   - User.java → Entity class representing a system user (admin/assistant).
 - Repository
   - PrescriptionRepository.java → JPA repository for Prescription entity.
   - UserRepository.java → JPA repository for User entity.
 - Security
   - JwtAuthenticationFilter.java → Filter to validate JWT tokens on incoming requests.
   - JwtUtil.java → Utility class for generating & verifying JWT tokens.
 - Services
   - PrescriptionService.java → Business logic for handling prescriptions.
   - UserService.java → Business logic for user authentication and management.
 - Others
   - PrescriptionGenerationApplication.java → Main Spring Boot application runner.
   - application.properties → App configuration (DB, security, server port, etc.).
   - prescriptiondb.mv.db → H2 database file storing prescription & user data.

### Architecture or Pattern:
This project follows the Model–View–Controller (MVC) architecture, ensuring clean separation of concerns:
- Model (M)
  - Represents the application’s data and business objects.
  - Classes in Model/ (User.java, Prescription.java) define the structure of entities stored in the database.
  - Repository/ interfaces (UserRepository.java, PrescriptionRepository.java) handle persistence and database operations.
  - Services/ (UserService.java, PrescriptionService.java) contain the business logic connecting controllers to repositories.
- View (V)
  - The frontend React.js application (prescription-generation-frontend/) acts as the view layer.
  - Components like PrescriptionForm.js, PrescriptionList.js, and PrescriptionPerDay.js render data from APIs into user-friendly tables and forms.
   - React ensures a smooth user experience with styled UI and dynamic updates.
- Controller (C)
  - REST controllers in the backend (Controller/) manage incoming HTTP requests and map them to services.
  - AuthController.java → Handles authentication and JWT generation.
  - PrescriptionController.java → Handles CRUD operations and reporting for prescriptions.

- ### Future Scope:
 - In future pagination can be added in api if big data needs to be handled.
 - Caching can be integrated to reduce API call so that application performance gets better.
 - Unit tests may be written and github action flow and docker can be set up.
 - AWS deployment is also possible etc.

### Notes: 
``` bash

Benefits of MVC Here:

- Separation of Concerns → Business logic (Service), request handling (Controller), and data (Model) are decoupled.

- Scalability → Easy to extend features (new endpoints, new frontend views).

- Maintainability → Developers can work independently on frontend (View) or backend (Model & Controller).

- Security → JWT authentication handled at the controller and security layers without leaking into business logic.

```

   





