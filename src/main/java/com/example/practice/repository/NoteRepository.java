package com.example.practice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice.model.Note;

//Spring Data JPA
@Repository("noteRepository")
public interface NoteRepository extends JpaRepository<Note, Long> {
	
	List<Note> findNoteByAuthorId(Long id);
	// findAll()
	
	// findById(id)
	
	//save()
	
	//deleteById()
	
	
	//...other methods are auto-generated.
	
	
}
