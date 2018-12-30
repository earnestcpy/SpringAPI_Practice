package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice.model.Note;

//Spring Data JPA
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	
	// findAll()
	
	// findById(id)
	
	//save()
	
	//deleteById()
	
	
	//...other methods are auto-generated.
	
	

}
