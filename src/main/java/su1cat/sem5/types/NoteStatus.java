package su1cat.sem5.types;

public enum NoteStatus {
    NOT_DONE("not done"), IN_PROGRESS("in progress"), DONE("done");

    private final String label;

    NoteStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
