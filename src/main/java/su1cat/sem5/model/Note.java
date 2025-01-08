package su1cat.sem5.model;

import su1cat.sem5.types.NoteStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NoteStatus status;

    @Column(name = "date")
    private Date dateCreated = new Date();

    public Note(String description, NoteStatus status) {
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NoteStatus getStatus() {
        return status;
    }

    public void setStatus(NoteStatus status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Note replaceNullWithPrev(Note note) {
        Note replacedNote = new Note(
                (this.description != null ? this.description : note.description),
                (this.status != null ? this.status : note.status)
        );
        return replacedNote;
    }

    public boolean hasThisNulls() {
        return this.description == null || this.status == null;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
