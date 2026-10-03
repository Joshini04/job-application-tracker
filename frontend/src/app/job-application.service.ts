import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobApplication } from './job-application.model';

@Injectable({ providedIn: 'root' })
export class JobApplicationService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/api/applications';

  getAll(status?: string): Observable<JobApplication[]> {
    const url = status ? `${this.baseUrl}?status=${status}` : this.baseUrl;
    return this.http.get<JobApplication[]>(url);
  }

  create(app: JobApplication): Observable<JobApplication> {
    return this.http.post<JobApplication>(this.baseUrl, app);
  }

  update(id: number, app: JobApplication): Observable<JobApplication> {
    return this.http.put<JobApplication>(`${this.baseUrl}/${id}`, app);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}