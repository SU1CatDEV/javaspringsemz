package su1cat.sem5.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import su1cat.sem5.types.NoteStatus;

//@Entity
//@DiscriminatorValue("URGENT")
public class UrgentNote extends Note{

    public UrgentNote() {

    }

    public UrgentNote(String description, NoteStatus status) {
        this.description = description;
        this.status = status;
    }

    @Override
    public Note replaceNullWithPrev(Note note) {
        Note replacedNote = new UrgentNote(
                (this.description != null ? this.description : note.description),
                (this.status != null ? this.status : note.status)
        );
        return replacedNote;
    }
}

