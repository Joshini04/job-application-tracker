# Job Application Tracker

A full-stack web app to track job applications: add, edit, delete and filter them by status.

**Stack:** Angular · Spring Boot · Spring Data JPA · MySQL

## Features
- Add, edit and delete job applications (full CRUD)
- Filter applications by status (Applied, Interview, Offer, Rejected)
- REST API built with Spring Boot, with data stored in MySQL
- Angular frontend calling the API through `HttpClient`

## Screenshot
![Job Application Tracker](screenshot.png)

## Project structure
```
backend/    Spring Boot REST API (Java, Spring Data JPA, MySQL)
frontend/   Angular app
```

## API endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/applications` | List all applications |
| GET | `/api/applications?status=Interview` | Filter by status |
| POST | `/api/applications` | Create an application |
| PUT | `/api/applications/{id}` | Update an application |
| DELETE | `/api/applications/{id}` | Delete an application |

## Running locally

**Requirements:** JDK 17+, Node.js 20+, MySQL 8, Angular CLI (`npm install -g @angular/cli`)

**1. Database**
```sql
CREATE DATABASE jobtracker;
```

**2. Backend**
1. Open `backend/src/main/resources/application.properties` and set your MySQL password:
   `spring.datasource.password=YOUR_MYSQL_PASSWORD`
2. Run `JobtrackerApplication` from your IDE, or from the `backend` folder:
```
   ./mvnw spring-boot:run
```
The API runs at `http://localhost:8080`. The `job_application` table is created automatically.

**3. Frontend**
```
cd frontend
npm install
ng serve
```
Open `http://localhost:4200`.

If `npm install` fails with an `edgesOut` error, use `npm install --legacy-peer-deps`.

## What I learned
- Building a REST API with Spring Boot and Spring Data JPA
- Connecting a Spring Boot app to MySQL
- Configuring CORS so an Angular app can call the API
- Angular basics: components, services, `HttpClient`, signals and `ngModel`
