package su1cat.sem5.model;

public class NormalNoteFactory implements NoteFactory{

    @Override
    public NormalNote createNote() {
        return new NormalNote();
    }

    @Override
    public NormalNote createFromOther(Note note) {
        NormalNote normalNote = new NormalNote();
        normalNote.setId(note.getId());
        normalNote.setDescription(note.getDescription());
        normalNote.setStatus(note.getStatus());
        return normalNote;
    }
}
