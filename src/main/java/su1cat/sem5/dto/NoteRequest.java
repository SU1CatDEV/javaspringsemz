package su1cat.sem5.dto;

import su1cat.sem5.model.NormalNote;

public class NoteRequest {
    private NormalNote note;
    private Boolean urgent;

    public NormalNote getNote() {
        return note;
    }

    public void setNote(NormalNote note) {
        this.note = note;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }
}
