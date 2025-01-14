package su1cat.sem5.repository;

import org.springframework.stereotype.Repository;
import su1cat.sem5.aspects.TrackUserAction;
import su1cat.sem5.model.Note;
import su1cat.sem5.types.NoteStatus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class NoteManager {
    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private Long lastId = 1L;

    protected NoteManager() {
        // Private constructor to enforce singleton pattern
    }

    public synchronized List<Note> findAllNotes() {
        return new ArrayList<>(notes.values());
    }

    public synchronized List<Note> findNoteByStatus(NoteStatus status) {
        List<Note> filteredNotes = new ArrayList<>();
        for (Note note : notes.values()) {
            if (note.getStatus() == status) {
                filteredNotes.add(note);
            }
        }
        return filteredNotes;
    }

    @TrackUserAction
    public synchronized Note createNote(Note note) {
        note.setId(lastId);
        notes.put(lastId, note);
        lastId++;
        return note;
    }

    @TrackUserAction
    public synchronized Note updateNote(Long id, Note moddedNote) {
        Note existingNote = notes.get(id);
        if (existingNote != null) {
            existingNote.setDescription(moddedNote.getDescription());
            existingNote.setStatus(moddedNote.getStatus());
        }
        return existingNote;
    }

    @TrackUserAction
    public synchronized void deleteNote(Long id) {
        notes.remove(id);
    }

    public synchronized Note findNoteById(Long id) {
        return notes.get(id);
    }

    public synchronized boolean exists(Long id) {
        return notes.containsKey(id);
    }
}
