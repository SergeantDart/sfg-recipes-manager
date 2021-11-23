package guru.springframework.springrecipeapp.converter;

import guru.springframework.springrecipeapp.command.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String UOM_VALUE = "uom";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testConvert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID_VALUE);
        unitOfMeasure.setUom(UOM_VALUE);

        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        assertNotNull(unitOfMeasureCommand);
        assertEquals(ID_VALUE, unitOfMeasureCommand.getId());
        assertEquals(UOM_VALUE, unitOfMeasureCommand.getUom());
    }

    @Test
    public void testConvertNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvertEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }
}