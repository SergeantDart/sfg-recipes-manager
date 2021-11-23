package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.CategoryCommand;
import guru.springframework.springrecipeapp.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = 1L;
    public static final String NAME_VALUE = "name";
    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testConvertEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void testConvertNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testConvert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setName(NAME_VALUE);

        Category category = converter.convert(categoryCommand);

        assertNotNull(category);
        assertEquals(ID_VALUE, category.getId());
        assertEquals(NAME_VALUE, category.getName());
    }
}