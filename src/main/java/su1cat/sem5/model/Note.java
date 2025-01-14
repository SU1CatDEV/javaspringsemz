package su1cat.sem5.model;

import su1cat.sem5.types.NoteStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "task_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "description", length = 2000)
    protected String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    protected NoteStatus status;

    @Column(name = "date")
    protected Date dateCreated = new Date();

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

    public abstract Note replaceNullWithPrev(Note note);

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
