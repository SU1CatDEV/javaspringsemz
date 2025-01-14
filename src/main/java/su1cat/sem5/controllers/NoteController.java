package su1cat.sem5.controllers;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import su1cat.sem5.model.*;
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
    public String createNoteForm(NormalNote note, Model model) {
        model.addAttribute("statuses", NoteStatus.values());
        return "note-create";
    }

    @PostMapping("/note-create")
    public String createNote(@ModelAttribute NormalNote note, @RequestParam Boolean isUrgent) {
//        UrgentNoteFactory urgentNoteFactory = new UrgentNoteFactory();
//        NormalNoteFactory normalNoteFactory = new NormalNoteFactory();
        if (isUrgent) {
            UrgentNote urgentNote = new UrgentNote();
            urgentNote.setDescription(note.getDescription());
            urgentNote.setStatus(note.getStatus());
            noteService.createNote(urgentNote);
        } else {
            noteService.createNote(note);
        }
        return "redirect:/notes";
    }

    @GetMapping("/note-update/{noteId}")
    public String updateNote(@PathVariable(value="noteId") Long id, Model model) {
        Note editing = noteService.findNoteById(id);
        model.addAttribute("editing", editing);
        model.addAttribute("statuses", NoteStatus.values());
        return "note-update";
    }

    @PostMapping("/note-update")
    public String updateNote(Note note) {
        noteService.updateNote(note.getId(), note);
        return "redirect:/notes";
    }

    @GetMapping("/note-delete/{noteId}")
    public String deleteNote(@PathVariable(value="noteId") Long id, Model model) {
        noteService.deleteNote(id);
        model.addAttribute("id", id);
        return "note-delete";
    }
}
