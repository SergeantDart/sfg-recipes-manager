package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.CategoryCommand;
import guru.springframework.springrecipeapp.command.IngredientCommand;
import guru.springframework.springrecipeapp.command.NoteCommand;
import guru.springframework.springrecipeapp.command.RecipeCommand;
import guru.springframework.springrecipeapp.model.Difficulty;
import guru.springframework.springrecipeapp.model.Note;
import guru.springframework.springrecipeapp.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xmlunit.diff.Diff;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    private static final Long RECIPE_ID_VALUE = 1L;
    private static final Integer COOK_TIME_VALUE = 5;
    private static final Integer PREP_TIME_VALUE = 8;
    private static final String TITLE_VALUE = "title";
    private static final String DIRECTIONS_VALUE = "directions";
    private static final Difficulty DIFFICULTY_VALUE = Difficulty.EASY;
    private static final Integer SERVINGS_VALUE = 3;
    public static final String SOURCE_VALUE = "source";
    public static final String URL_VALUE = "url";
    public static final Long CATEGORY_ID_VALUE_1 = 1L;
    public static final Long CATEGORY_ID_VALUE_2 = 2L;
    public static final Long INGREDIENT_ID_VALUE_1 = 1L;
    public static final Long INGREDIENT_ID_VALUE_2 = 2L;
    public static final Long NOTES_ID_VALUE = 1L;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(), new NoteCommandToNote(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    void testConvert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID_VALUE);
        recipeCommand.setCookTime(COOK_TIME_VALUE);
        recipeCommand.setPrepTime(PREP_TIME_VALUE);
        recipeCommand.setSource(SOURCE_VALUE);
        recipeCommand.setUrl(URL_VALUE);
        recipeCommand.setServings(SERVINGS_VALUE);
        recipeCommand.setTitle(TITLE_VALUE);
        recipeCommand.setDirections(DIRECTIONS_VALUE);
        recipeCommand.setDifficulty(DIFFICULTY_VALUE);

        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(NOTES_ID_VALUE);

        recipeCommand.setNote(noteCommand);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_ID_VALUE_1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_ID_VALUE_2);

        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_ID_VALUE_1);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGREDIENT_ID_VALUE_2);

        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        Recipe recipe = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID_VALUE, recipe.getId());
        assertEquals(TITLE_VALUE, recipe.getTitle());
        assertEquals(PREP_TIME_VALUE, recipe.getPrepTime());
        assertEquals(COOK_TIME_VALUE, recipe.getCookTime());
        assertEquals(SERVINGS_VALUE, recipe.getServings());
        assertEquals(SOURCE_VALUE, recipe.getSource());
        assertEquals(URL_VALUE, recipe.getUrl());
        assertEquals(DIRECTIONS_VALUE, recipe.getDirections());
        assertEquals(DIFFICULTY_VALUE, recipe.getDifficulty());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }

    @Test
    public void testConvertNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvertEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }
}