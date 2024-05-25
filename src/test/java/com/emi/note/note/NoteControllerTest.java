package com.emi.note.note;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {

    @MockBean
    NoteService noteService;

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetAllNotesSuccess() throws Exception {
        Note note = Note.builder().id(1).title("Hello").body("World").build();

        when(noteService.getAllNotes()).thenReturn(Arrays.asList(note));

        mvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].title").value("Hello"))
                .andExpect(jsonPath("$.[0].body").value("World"));
    }

    @Test
    public void testGetNoteByIdNoNoteFoundWillReturnNotContent() throws Exception {
        when(noteService.getNoteById(anyInt())).thenThrow(NoteNotFoundException.class);

        mvc.perform(get("/notes/1")).andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateNoteNoNoteFoundWillReturnNotContent() throws Exception{
       doThrow(NoteNotFoundException.class)
               .when(noteService).updateNote(anyInt(),any());

        mvc.perform(put("/notes/1")).andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteNoteNoNoteFoundWillReturnNotContent() throws Exception{
        doThrow(NoteNotFoundException.class)
                .when(noteService).deleteNote(anyInt());

        mvc.perform(delete("/notes/1")).andExpect(status().isNoContent());
    }

}