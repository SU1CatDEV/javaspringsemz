package su1cat.sem5.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import su1cat.sem5.types.NoteStatus;

@Entity
@DiscriminatorValue("NORMAL")
public class NormalNote extends Note{

    public NormalNote() {

    }

    public NormalNote(String description, NoteStatus status) {
        this.description = description;
        this.status = status;
    }

    @Override
    public Note replaceNullWithPrev(Note note) {
        Note replacedNote = new NormalNote(
                (this.description != null ? this.description : note.description),
                (this.status != null ? this.status : note.status)
        );
        return replacedNote;
    }
}

