package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.RecipeCommand;
import guru.springframework.springrecipeapp.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final NoteToNoteCommand noteConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, NoteToNoteCommand noteConverter, IngredientToIngredientCommand ingredientConverter) {
        this.categoryConverter = categoryConverter;
        this.noteConverter = noteConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setTitle(source.getTitle());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setNote(noteConverter.convert(source.getNote()));

        if(source.getIngredients() != null & source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        if(source.getCategories() != null & source.getCategories().size() > 0) {
            source.getCategories().forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }

        return recipeCommand;
    }
}
