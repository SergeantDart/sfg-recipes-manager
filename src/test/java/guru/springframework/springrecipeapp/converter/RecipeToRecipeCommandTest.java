package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.CategoryCommand;
import guru.springframework.springrecipeapp.command.IngredientCommand;
import guru.springframework.springrecipeapp.command.NoteCommand;
import guru.springframework.springrecipeapp.command.RecipeCommand;
import guru.springframework.springrecipeapp.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {
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

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(), new NoteToNoteCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    void testConvert() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID_VALUE);
        recipe.setCookTime(COOK_TIME_VALUE);
        recipe.setPrepTime(PREP_TIME_VALUE);
        recipe.setSource(SOURCE_VALUE);
        recipe.setUrl(URL_VALUE);
        recipe.setServings(SERVINGS_VALUE);
        recipe.setTitle(TITLE_VALUE);
        recipe.setDirections(DIRECTIONS_VALUE);
        recipe.setDifficulty(DIFFICULTY_VALUE);

        Note note = new Note();
        note.setId(NOTES_ID_VALUE);

        recipe.setNote(note);

        Category category1= new Category();
        category1.setId(CATEGORY_ID_VALUE_1);
        Category category2 = new Category();
        category2.setId(CATEGORY_ID_VALUE_2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID_VALUE_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_VALUE_2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        RecipeCommand recipeCommand = converter.convert(recipe);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID_VALUE, recipeCommand.getId());
        assertEquals(TITLE_VALUE, recipeCommand.getTitle());
        assertEquals(PREP_TIME_VALUE, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME_VALUE, recipeCommand.getCookTime());
        assertEquals(SERVINGS_VALUE, recipeCommand.getServings());
        assertEquals(SOURCE_VALUE, recipeCommand.getSource());
        assertEquals(URL_VALUE, recipeCommand.getUrl());
        assertEquals(DIRECTIONS_VALUE, recipeCommand.getDirections());
        assertEquals(DIFFICULTY_VALUE, recipeCommand.getDifficulty());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
    }

    @Test
    public void testConvertNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvertEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }
}