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

    @GetMapping("/{noteId}")
    public Note getNoteById(@Valid @PathVariable("noteId") int noteId) throws NoteNotFoundException {
        return noteService.getNoteById(noteId);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<String> updateNote(@PathVariable("noteId") int noteId,@Valid NoteRequestBody noteRequestBody) throws NoteNotFoundException {
        noteService.updateNote(noteId, noteRequestBody);
        return new ResponseEntity<>("Updated "+noteId, HttpStatus.OK);
    }

    @DeleteMapping("/{noteId}")
    public Note deleteNote(@PathVariable("noteId")int noteId) throws NoteNotFoundException {
        return noteService.deleteNote(noteId);
    }
}
