package guru.springframework.springrecipeapp.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteCommand {

    private Long id;
    private String description;
}
