package com.example.practice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.exception.AuthorNotFoundException;
import com.example.practice.exception.ResourceNotFoundException;
import com.example.practice.model.Note;
import com.example.practice.repository.AuthorRepository;
import com.example.practice.repository.NoteRepository;

@CrossOrigin(origins = "http://localhost:4200")  // Spring boot, by default, only allows connections from same domain and port. // CrossOrigin allows connections from other domains defined in "origins"
@RestController
@RequestMapping("/notes_api")
public class NoteController {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@GetMapping("/notes")
	public List<Note> getAllNotes() {
	    return noteRepository.findAll();
	}
	
	@GetMapping("/author/{author_id}/notes")
	public List<Note>getAllNotesByID(@PathVariable(name ="author_id") Long author_id){
		if(authorRepository.existsById(author_id)) {
			return noteRepository.findNoteByAuthorId(author_id);
		}
		else {
			throw new AuthorNotFoundException(author_id);
		}
		
	}
	
	
	@PostMapping("/author/{author_id}/notes")
	public Note createNote(@PathVariable(value = "author_id") Long author_id, @Valid @RequestBody Note  note ) {
		
		return authorRepository.findById(author_id)
					.map(author ->{
						note.setAuthor(author);
						return noteRepository.save(note);
					}).orElseThrow(() -> new AuthorNotFoundException(author_id));
	}	
	
	// Update a Note
	@PutMapping("/author/{author_id}/notes/{note_id}")
	public Optional<Note> updateNote(@PathVariable(value = "note_id") Long noteId
			, @PathVariable(value = "author_id") Long author_id
			, @Valid @RequestBody Note updated_note) {

		if(!authorRepository.existsById(author_id)) {
			throw new AuthorNotFoundException(author_id);
		}
		else if(!noteRepository.existsById(noteId)) {
			throw new ResourceNotFoundException("Note", "id", noteId);
			
		}
		else 
			return noteRepository.findById(noteId)
					.map(old_note ->{
						old_note.setContent(updated_note.getContent());
						old_note.setTitle(updated_note.getTitle());
						return noteRepository.save(old_note);
					});
}
	
	
	
	
	
	@DeleteMapping("/author/{author_id}/notes/{note_id}")
	public  Optional<String> deleteNote(@PathVariable(value = "note_id") Long noteId
			, @PathVariable(value = "author_id") Long author_id) {

		if(!authorRepository.existsById(author_id)) {
			throw new AuthorNotFoundException(author_id);
		}
		else {
			return noteRepository.findById(noteId)
					.map(note ->{
						noteRepository.delete(note);
						return "Delete Successfully";
					});
		}
			
	}
}
