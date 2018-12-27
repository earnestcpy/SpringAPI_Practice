package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	

}
