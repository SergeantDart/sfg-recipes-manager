package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.NoteCommand;
import guru.springframework.springrecipeapp.model.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note source) {
        if(source == null) {
            return null;
        }

        final NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(source.getId());
        noteCommand.setDescription(source.getDescription());
        return noteCommand;
    }
}
