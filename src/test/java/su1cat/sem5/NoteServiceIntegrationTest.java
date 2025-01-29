package su1cat.sem5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import su1cat.sem5.model.NormalNote;
import su1cat.sem5.model.Note;
import su1cat.sem5.repository.NoteRepository;
import su1cat.sem5.services.NoteService;
import su1cat.sem5.types.NoteStatus;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class NoteServiceIntegrationTest {
    @MockitoBean
    public NoteRepository noteManager;

    @Autowired
    public NoteService noteService;

    @Test
    public void createNoteTest() {
        // pre

        // action
        Note note = new NormalNote();
        note.setId(1L);
        note.setDescription("Note 1 description");
        note.setStatus(NoteStatus.NOT_DONE);

        noteService.createNote(note);

        // check
        verify(noteManager).save(note);
    }

    @Test
    public void updateNoteTest() {
        // pre
        Note note = new NormalNote();
        note.setId(2L);
        note.setDescription("Note 2 description");
        note.setStatus(NoteStatus.NOT_DONE);

        // action

        note.setStatus(NoteStatus.DONE);
        noteService.updateNote(note);

        // check
        verify(noteManager).save(note);
    }

    @Test
    public void deleteNoteTest() {
        // pre
        Note note = new NormalNote();
        note.setId(3L);
        note.setDescription("Note 3 description");
        note.setStatus(NoteStatus.NOT_DONE);
        given(noteManager.save(note)).willReturn(note);
        given(noteManager.existsById(note.getId())).willReturn(true);

        // action
        noteService.createNote(note);
        assertTrue(noteService.exists(note.getId()));
        noteService.deleteNote(note.getId());

        // check
        verify(noteManager).deleteById(note.getId());

    }
}
