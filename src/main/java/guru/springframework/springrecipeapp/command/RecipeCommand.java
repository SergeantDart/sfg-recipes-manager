package guru.springframework.springrecipeapp.command;

import guru.springframework.springrecipeapp.model.Category;
import guru.springframework.springrecipeapp.model.Difficulty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RecipeCommand {
    private Long id;
    private String title;
    private Integer servings;
    private Integer prepTime;
    private Integer cookTime;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NoteCommand note = new NoteCommand();
    private Set<CategoryCommand> categories = new HashSet<>();
}
