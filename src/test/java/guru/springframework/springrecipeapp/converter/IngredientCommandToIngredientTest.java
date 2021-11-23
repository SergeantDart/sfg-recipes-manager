package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.IngredientCommand;
import guru.springframework.springrecipeapp.command.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.model.Ingredient;
import guru.springframework.springrecipeapp.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE_VALUE = new Recipe();
    public static final BigDecimal AMOUNT_VALUE = new BigDecimal("1");
    public static final String DESCRIPTION_VALUE = "description";
    public static final Long ID_VALUE = 1L;
    public static final Long UOM_ID_VALUE = 2L;

    IngredientCommandToIngredient converter;

    @BeforeEach
    public void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testConvertWithNotNullUnitOfMeasure() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setAmount(AMOUNT_VALUE);
        ingredientCommand.setDescription(DESCRIPTION_VALUE);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID_VALUE);

        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);

        Ingredient ingredient = converter.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT_VALUE, ingredient.getAmount());
        assertEquals(DESCRIPTION_VALUE, ingredient.getDescription());
        assertEquals(UOM_ID_VALUE, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    public void testConvertWithNullUnitOfMeasure() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setAmount(AMOUNT_VALUE);
        ingredientCommand.setDescription(DESCRIPTION_VALUE);


        Ingredient ingredient = converter.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT_VALUE, ingredient.getAmount());
        assertEquals(DESCRIPTION_VALUE, ingredient.getDescription());
    }

    @Test
    public void testConvertNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvertEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }
}