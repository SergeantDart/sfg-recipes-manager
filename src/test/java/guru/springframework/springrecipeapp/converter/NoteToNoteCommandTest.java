package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.NoteCommand;
import guru.springframework.springrecipeapp.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteToNoteCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION_VALUE = "notes";
    NoteToNoteCommand converter;


    @BeforeEach
    void setUp() {
        converter = new NoteToNoteCommand();
    }

    @Test
    void testConvertNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testConvertEmptyObject() {
        assertNotNull(converter.convert(new Note()));
    }

    @Test
    void testConvert() {
        Note note = new Note();
        note.setId(ID_VALUE);
        note.setDescription(DESCRIPTION_VALUE);

        NoteCommand noteCommand = converter.convert(note);

        assertNotNull(noteCommand);
        assertEquals(ID_VALUE, noteCommand.getId());
        assertEquals(DESCRIPTION_VALUE, noteCommand.getDescription());
    }
}