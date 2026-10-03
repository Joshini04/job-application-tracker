import { Component, OnInit, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobApplicationService } from './job-application.service';
import { JobApplication } from './job-application.model';

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  private service = inject(JobApplicationService);

  applications = signal<JobApplication[]>([]);
  selectedStatus = '';

  form: JobApplication = this.emptyForm();
  editingId: number | null = null;

  ngOnInit() {
    this.load();
  }

  emptyForm(): JobApplication {
    return { company: '', role: '', status: 'Applied', appliedDate: '', notes: '' };
  }

  load() {
    this.service.getAll(this.selectedStatus).subscribe(data => this.applications.set(data));
  }

  onFilterChange(event: Event) {
    this.selectedStatus = (event.target as HTMLSelectElement).value;
    this.load();
  }

  save() {
    const request = this.editingId !== null
      ? this.service.update(this.editingId, this.form)
      : this.service.create(this.form);

    request.subscribe(() => {
      this.resetForm();
      this.load();
    });
  }

  edit(app: JobApplication) {
    this.editingId = app.id ?? null;
    this.form = { ...app };
  }

  resetForm() {
    this.form = this.emptyForm();
    this.editingId = null;
  }

  remove(id: number | undefined) {
    if (id === undefined) return;
    this.service.delete(id).subscribe(() => this.load());
  }
}