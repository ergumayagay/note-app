package com.emi.note.note;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Note {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=1)
    @NotEmpty
    @NotNull
    private String title;

    @Size(min=1)
    @NotEmpty
    @NotNull
    private String body;

    public static Note of(NoteRequestBody requestBody){
        return Note.builder().body(requestBody.getBody())
                .title(requestBody.getTitle()).build();
    }
}
