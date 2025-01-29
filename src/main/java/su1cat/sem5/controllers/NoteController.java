package su1cat.sem5.controllers;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import su1cat.sem5.model.*;
import su1cat.sem5.model.exceptions.NoteNotFoundException;
import su1cat.sem5.services.NoteService;
import su1cat.sem5.types.NoteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class NoteController {
    @Autowired
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String notes(Model model) {
        model.addAttribute("notes", noteService.findAllNotes());
        return "note-list";
    }

    @GetMapping("/note-create")
    public String createNoteForm(Model model) {
        model.addAttribute("note", new NormalNote());
        model.addAttribute("statuses", NoteStatus.values());
        return "note-create";
    }


    @PostMapping("/note-create")
    public String createNote(@ModelAttribute NormalNote note, @RequestParam(name = "urgent", defaultValue = "false") Boolean isUrgent) {
        if (note.getDescription().length() > 2000) {
            return "note-error.html";
        }
        UrgentNoteFactory urgentNoteFactory = new UrgentNoteFactory(); // пожалуй не самый элегантный способ, но однако абстрактный класс Thymeleaf не дает передавать в темплейт.
        if (isUrgent) {
            UrgentNote urgentNote = urgentNoteFactory.createFromOther(note);
            noteService.createNote(urgentNote);
        } else {
            noteService.createNote(note);
        }
        return "redirect:/notes";
    }

    @GetMapping("/note-update/{noteId}")
    public String updateNote(@PathVariable(value="noteId") Long id, Model model) {
        Note editing = noteService.findNoteById(id);
        if (editing != null) {
            model.addAttribute("editing", editing);
        } else {
            throw new NoteNotFoundException();
        }
        model.addAttribute("statuses", NoteStatus.values());
        return "note-update";
    }

    @PostMapping("/note-update")
    public String updateNote(@ModelAttribute NormalNote note) {
        if (note.getDescription().length() > 2000) {
            return "note-error.html";
        }
        UrgentNoteFactory urgentNoteFactory = new UrgentNoteFactory();
        if (noteService.findNoteById(note.getId()) instanceof UrgentNote) {
            UrgentNote urgentNote = urgentNoteFactory.createFromOther(note);
            noteService.updateNote(urgentNote);
        } else {
            noteService.updateNote(note);
        }
        return "redirect:/notes";
    }

    @GetMapping("/note-delete/{noteId}")
    public String deleteNote(@PathVariable(value="noteId") Long id, Model model) {
        noteService.deleteNote(id);
        model.addAttribute("id", id);
        return "note-delete";
    }
}
