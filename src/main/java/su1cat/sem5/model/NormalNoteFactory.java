package su1cat.sem5.model;

public class NormalNoteFactory implements NoteFactory{

    @Override
    public NormalNote createNote() {
        return new NormalNote();
    }
}
