package su1cat.sem5.model;

public class UrgentNoteFactory implements NoteFactory{

    @Override
    public UrgentNote createNote() {
        return new UrgentNote();
    }

    @Override
    public UrgentNote createFromOther(Note note) {
        UrgentNote urgentNote = new UrgentNote();
        urgentNote.setId(note.getId());
        urgentNote.setDescription(note.getDescription());
        urgentNote.setStatus(note.getStatus());
        return urgentNote;
    }
}
