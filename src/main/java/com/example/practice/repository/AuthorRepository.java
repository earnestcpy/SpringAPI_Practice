package com.example.practice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.practice.model.Author;


//Spring Data JPA
@Repository("authorRepository")
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	Author findAuthorByName(String name);


}
