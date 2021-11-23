package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.IngredientCommand;
import guru.springframework.springrecipeapp.command.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.model.Ingredient;
import guru.springframework.springrecipeapp.model.Recipe;
import guru.springframework.springrecipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Recipe RECIPE_VALUE = new Recipe();
    public static final BigDecimal AMOUNT_VALUE = new BigDecimal("1");
    public static final String DESCRIPTION_VALUE = "description";
    public static final Long ID_VALUE = 1L;
    public static final Long UOM_ID_VALUE = 2L;

    IngredientToIngredientCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testConvertWithNotNullUnitOfMeasure() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE_VALUE);
        ingredient.setAmount(AMOUNT_VALUE);
        ingredient.setDescription(DESCRIPTION_VALUE);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID_VALUE);

        ingredient.setUnitOfMeasure(unitOfMeasure);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT_VALUE, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION_VALUE, ingredientCommand.getDescription());
        assertEquals(UOM_ID_VALUE, ingredientCommand.getUnitOfMeasure().getId());
    }

    @Test
    public void testConvertWithNullUnitOfMeasure() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE_VALUE);
        ingredient.setAmount(AMOUNT_VALUE);
        ingredient.setDescription(DESCRIPTION_VALUE);
        ingredient.setUnitOfMeasure(null);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT_VALUE, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION_VALUE, ingredientCommand.getDescription());
    }

    @Test
    public void testConvertNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvertEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }
}