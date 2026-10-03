package com.example.jobtracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //means its methods handle web requests and return JSON.
@RequestMapping("/api/applications") //base URL for everything
public class JobApplicationController {

    //The constructor (dependency injection) (the below 2 lines)
    private final JobApplicationRepository repo;
    public JobApplicationController(JobApplicationRepository repo) {
        this.repo = repo;
    }

    //The controller needs the repository to talk to the database.
    //Instead of writing new JobApplicationRepository() yourself, you just say "I need one" in the constructor
    //and Spring creates it and hands it over.


    @GetMapping //returns the list => return all of them, or only those with a matching status
    public List<JobApplication> getAll(@RequestParam(required = false) String status) {
        return (status == null || status.isEmpty()) ? repo.findAll() : repo.findByStatus(status);
    }

    @PostMapping //create => Someone sends a new application as JSON.
    //@RequestBody tells Spring to turn that JSON into a JobApplication object
    public JobApplication create(@RequestBody JobApplication app) {
        return repo.save(app); //stores it as a new row.
    }

    @PutMapping("/{id}") //update
    //Someone sends changed data for application number 5 =>via PUT /api/applications/5
    //Looks up row 5 (findById).
    //If it exists, copies the new values onto it and saves it.
    //If not, returns 404 ("not found").
    public ResponseEntity<JobApplication> update(@PathVariable Long id,
                                                 @RequestBody JobApplication updated) {
        return repo.findById(id).map(existing -> {
            existing.setCompany(updated.getCompany());
            existing.setRole(updated.getRole());
            existing.setStatus(updated.getStatus());
            existing.setAppliedDate(updated.getAppliedDate());
            existing.setNotes(updated.getNotes());
            return ResponseEntity.ok(repo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") //delete
    //DELETE /api/applications/5 removes row 5.
    //If row 5 doesn't exist, it returns 404.
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

// SOME IMPORTANTS TERMS HERE FOR UNDERSTANDING!!
//{id} =>is a placeholder in the URL
// placeholder =>a temporary variable inside a web address that gets replaced with real data
//@PathVariable Long id  => grabs whatever number is there

// Dependency injection(constructor)
//
//App starts
//   ↓
//Spring creates the repository (once)
//   ↓
//Spring creates the controller and passes the repository in
//   ↓
//Controller uses it for every request