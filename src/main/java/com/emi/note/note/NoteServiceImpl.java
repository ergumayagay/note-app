package com.emi.note.note;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void createNote(NoteRequestBody noteRequestBody){
        noteRepository.save(Note.of(noteRequestBody));
    }

    @Transactional
    @Override
    public Note getNoteById(int noteId) throws NoteNotFoundException {
        return noteRepository.findById(noteId)
                .orElseThrow(()-> new NoteNotFoundException(noteId+ " not found."));
    }

    @Transactional
    @Override
    public void updateNote(int noteId, NoteRequestBody noteRequestBody) throws NoteNotFoundException {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(()-> new NoteNotFoundException(noteId+ " not found."));

        note.setTitle(noteRequestBody.getTitle());
        note.setBody(noteRequestBody.getBody());
        noteRepository.save(note);
    }

    @Transactional
    @Override
    public Note deleteNote(int noteId) throws NoteNotFoundException {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(()-> new NoteNotFoundException(noteId+" not found."));

        noteRepository.delete(note);
        return note;
    }
}
