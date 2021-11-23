package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.CategoryCommand;
import guru.springframework.springrecipeapp.command.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.model.Category;
import guru.springframework.springrecipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String NAME_VALUE = "name";
    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testConvertEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void testConvertNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testConvert() {
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setName(NAME_VALUE);

        CategoryCommand categoryCommand = converter.convert(category);

        assertNotNull(category);
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(NAME_VALUE, categoryCommand.getName());
    }
}