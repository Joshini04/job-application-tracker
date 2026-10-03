package com.example.jobtracker;
//It's an interface with no code inside
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStatus(String status);
    //when repo.findByStatus("Applied") called,
    //Spring runs:SELECT * FROM job_application WHERE status = 'Applied'
    //findByStatus=> search for rows
}

//By extending JpaRepository<JobApplication, Long>,you get save, findAll, findById, deleteById and more for free.
//JpaRepository is built into the Spring Data JPA library, the dependency  added at start.spring.io.

//By extending the JpaRepository => the spring data JPA creates repository class and the
// Hibernate (the JPA library underneath) writes SQL code but we cant see
// while run => if the controller has of like repo.save() or repo.findALL() triggers INSERT/SELECT

