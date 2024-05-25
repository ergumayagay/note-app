package com.emi.note.note;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }

    @PostMapping
    public ResponseEntity<String> createNote(@Valid NoteRequestBody note){
        noteService.createNote(note);
        return new ResponseEntity<>("Note Created",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Note getNoteById(@Valid @PathVariable("id") int id) throws NoteNotFoundException {
        return noteService.getNoteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNote(@PathVariable("id") int id,
                                             @Valid NoteRequestBody noteRequestBody) throws NoteNotFoundException {
        noteService.updateNote(id, noteRequestBody);
        return new ResponseEntity<>("Updated "+id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Note deleteNote(@PathVariable("id")int id) throws NoteNotFoundException {
        return noteService.deleteNote(id);
    }
}
