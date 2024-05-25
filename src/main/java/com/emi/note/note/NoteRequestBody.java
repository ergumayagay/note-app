package com.emi.note.note;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoteRequestBody {

    @Size(min=1)
    @NotEmpty(message = "title is mandatory")
    String title;

    @Size(min=1)
    @NotEmpty(message = "body is mandatory")
    String body;
}
