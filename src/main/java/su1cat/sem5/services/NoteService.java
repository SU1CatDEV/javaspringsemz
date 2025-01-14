package su1cat.sem5.services;

//import su1cat.sem5.aspects.TrackUserAction;
import su1cat.sem5.aspects.TrackUserAction;
import su1cat.sem5.model.Note;
import su1cat.sem5.repository.NoteManager;
import su1cat.sem5.types.NoteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private final NoteManager noteManager;

    public NoteService(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    public List<Note> findAllNotes() {
        return noteManager.findAllNotes();
    }

    public List<Note> findNoteByStatus(NoteStatus status) {
        return noteManager.findNoteByStatus(status);
    }

    @TrackUserAction
    public Note createNote(Note note) {
        return noteManager.createNote(note);
    }

    @TrackUserAction
    public Note updateNote(Long id, Note moddedNote) {
        return noteManager.updateNote(id, moddedNote);
    }

    public void deleteNote(Long id) {
        if (noteManager.exists(id)) {
            noteManager.deleteNote(id);
        }
    }

    public Note findNoteById(Long id) {
        return noteManager.findNoteById(id);
    }

    public boolean exists(Long id) {
        return noteManager.exists(id);
    }
}


//package su1cat.sem5.services;
//
//        import su1cat.sem5.aspects.TrackUserAction;
//        import su1cat.sem5.model.Note;
//        import su1cat.sem5.repository.NoteManager;
//        import su1cat.sem5.types.NoteStatus;
//        import org.springframework.stereotype.Service;
//
//        import java.util.List;
//
//@Service
//public class NoteService {
//    private final NoteManager noteManager = NoteManager.getInstance();
//
//    public List<Note> findAllNotes() {
//        return noteManager.findAllNotes();
//    }
//
//    public List<Note> findNoteByStatus(NoteStatus status) {
//        return noteManager.findNoteByStatus(status);
//    }
//
//    @TrackUserAction
//    public Note createNote(Note note) {
//        return noteManager.createNote(note);
//    }
//
//    @TrackUserAction
//    public Note updateNote(Long id, Note moddedNote) {
//        return noteManager.updateNote(id, moddedNote);
//    }
//
//    @TrackUserAction
//    public void deleteNote(Long id) {
//        noteManager.deleteNote(id);
//    }
//
//    public Note findNoteById(Long id) {
//        return noteManager.findNoteById(id);
//    }
//
//    public boolean exists(Long id) {
//        return noteManager.exists(id);
//    }
//}
