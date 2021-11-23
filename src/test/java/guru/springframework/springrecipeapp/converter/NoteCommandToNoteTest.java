package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.NoteCommand;
import guru.springframework.springrecipeapp.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteCommandToNoteTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION_VALUE = "notes";
    NoteCommandToNote converter;


    @BeforeEach
    void setUp() {
        converter = new NoteCommandToNote();
    }

    @Test
    void testConvertNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testConvertEmptyObject() {
        assertNotNull(converter.convert(new NoteCommand()));
    }

    @Test
    void testConvert() {
        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(ID_VALUE);
        noteCommand.setDescription(DESCRIPTION_VALUE);

        Note note = converter.convert(noteCommand);

        assertNotNull(note);
        assertEquals(ID_VALUE, note.getId());
        assertEquals(DESCRIPTION_VALUE, note.getDescription());
    }
}