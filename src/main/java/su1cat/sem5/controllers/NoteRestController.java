package su1cat.sem5.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import su1cat.sem5.model.Note;
import su1cat.sem5.services.FileGateway;
import su1cat.sem5.services.NoteService;
import su1cat.sem5.types.NoteStatus;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteRestController {

    @Autowired
    private FileGateway fileGateway;

    @Autowired
    private final NoteService noteService;

    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> findAll() {
        return noteService.findAllNotes();
    }

    @GetMapping("/notes/{noteId}")
    public Note findById(@PathVariable("noteId") Long noteId) {
        return noteService.findNoteById(noteId);
    }

    @PostMapping("/notes")
    public ResponseEntity<Object> addNote(@RequestBody Note note) {
        if (note.hasThisNulls()) {
            return ResponseEntity.badRequest().body("Note has null values");
        }
        if (note.getDescription().length() > 10) {
            return ResponseEntity.badRequest().body("Note description exceeds 2000 characters");
        }
        Note createdNote = noteService.createNote(note); // separated in case something goes way too wrong. but thats not my problem.
        // thats the backend dev's job. we ignore the fact that IM doing the backend for the purposes of this thought experiment.
        fileGateway.writeToFile(note.getDescription() + ".txt", note.toString()); // todo: make title.
        return ResponseEntity.ok(createdNote);
    }

    // and this folks is why im allowed to call myself a programmer. because i copy pasted this thing from stackoverflow. types r hard.
    @PatchMapping("/notes/{noteId}")
    public ResponseEntity<Object> updateNote(@PathVariable("noteId") Long noteId, @RequestBody Note note) {
        if (!noteService.exists(noteId)) {
            return ResponseEntity.badRequest().body("Note does not exist");
        }
        if (note.getDescription().length() > 10) {
            return ResponseEntity.badRequest().body("Note description exceeds 2000 characters");
        }
        note = note.replaceNullWithPrev(noteService.findNoteById(noteId));
        Note updatedNote = noteService.updateNote(noteId, note);
        return ResponseEntity.ok(updatedNote);
    }


    @DeleteMapping("/notes/{noteId}")
    public void deleteNote(@PathVariable("noteId") Long noteId) {
        if (!noteService.exists(noteId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Note does not exist");
        }
        noteService.deleteNote(noteId);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException && cause.getMessage().contains("Enum")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid value for status. Accepted values are: " + Arrays.toString(NoteStatus.values()));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Malformed JSON request");
    }
}

