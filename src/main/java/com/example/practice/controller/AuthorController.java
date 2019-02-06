package com.example.practice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.exception.ResourceNotFoundException;
import com.example.practice.model.Author;
import com.example.practice.repository.AuthorRepository;


@CrossOrigin(origins = "http://localhost:4200")  // Spring boot, by default, only allows connections from same domain and port. // CrossOrigin allows connections from other domains defined in "origins"
@RestController
@RequestMapping("/authors_api")
public class AuthorController {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	
	@GetMapping("/authors")
	private List<Author> getAllauthor(){
		return authorRepository.findAll();
	}
	
	@GetMapping("/author/name/{name}")
	private Author getAuthorByName(@PathVariable(value = "name") String name) {
		return authorRepository.findAuthorByName(name);
	}
	
	@GetMapping("/author/{id}")
	private Author getAuthorById(@PathVariable(value = "id") Long id) {
		return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
	}
	
	
	@PostMapping("/author")
	private Author saveAuthor(@Valid @RequestBody Author temp_author) {
		return authorRepository.save(temp_author);
	}


}
