package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.NoteCommand;
import guru.springframework.springrecipeapp.model.Note;
import lombok.Synchronized;
import org.aspectj.weaver.ast.Not;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand source) {
        if(source == null) {
            return null;
        }

        final Note note = new Note();
        note.setId(source.getId());
        note.setDescription(source.getDescription());
        return note;
    }
}
