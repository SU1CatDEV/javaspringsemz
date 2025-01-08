package su1cat.sem5.services;

import su1cat.sem5.model.Note;
import su1cat.sem5.repository.NoteRepository;
import su1cat.sem5.types.NoteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> findNoteByStatus(NoteStatus status) {
        return noteRepository.findNoteByStatus(status);
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note moddedNote) {
        Note note = noteRepository.getReferenceById(id);
        note.setDescription(moddedNote.getDescription());
        note.setStatus(moddedNote.getStatus());
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        }
    }

    public Note findNoteById(Long id) {
        return noteRepository.findNoteById(id);
    }

    public boolean exists(Long id) {
        return noteRepository.existsById(id);
    }
}
