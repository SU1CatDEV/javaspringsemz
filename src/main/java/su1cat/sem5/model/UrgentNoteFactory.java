package su1cat.sem5.model;

public class UrgentNoteFactory implements NoteFactory{

    @Override
    public UrgentNote createNote() {
        return new UrgentNote();
    }
}
