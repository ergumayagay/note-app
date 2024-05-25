package com.emi.note.note;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();
    void createNote(NoteRequestBody noteRequestBody);
    Note getNoteById(int noteId) throws NoteNotFoundException;
    void updateNote(int noteId, NoteRequestBody noteRequestBody) throws NoteNotFoundException;
    Note deleteNote(int noteId) throws NoteNotFoundException;

}
