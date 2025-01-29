package su1cat.sem5.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import su1cat.sem5.types.NoteStatus;

@Entity
@DiscriminatorValue("NORMAL")
public class NormalNote extends Note{

    public NormalNote() {

    }

    public NormalNote(Long id, String description, NoteStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    @Override
    public NormalNote replaceNullWithPrev(Note note) {
        NormalNote replacedNote = new NormalNote(
                (this.id != null ? this.id : note.id),
                (this.description != null ? this.description : note.description),
                (this.status != null ? this.status : note.status)
        );
        return replacedNote;
    }
}

