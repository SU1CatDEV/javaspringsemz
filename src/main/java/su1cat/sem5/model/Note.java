package su1cat.sem5.model;

import su1cat.sem5.types.NoteStatus;
import jakarta.persistence.*;

import java.util.Date;
import lombok.Data;

@Entity
@Data
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
}
