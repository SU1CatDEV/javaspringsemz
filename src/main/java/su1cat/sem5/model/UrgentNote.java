package su1cat.sem5.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import su1cat.sem5.types.NoteStatus;

@Entity
@DiscriminatorValue("URGENT")
public class UrgentNote extends Note{

    public UrgentNote() {

    }

    public UrgentNote(Long id, String description, NoteStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    @Override
    public UrgentNote replaceNullWithPrev(Note note) {
        UrgentNote replacedNote = new UrgentNote(
                (this.id != null ? this.id : note.id),
                (this.description != null ? this.description : note.description),
                (this.status != null ? this.status : note.status)
        );
        return replacedNote;
    }
}

