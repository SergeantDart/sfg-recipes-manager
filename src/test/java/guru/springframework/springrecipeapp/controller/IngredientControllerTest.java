package guru.springframework.springrecipeapp.controller;

import guru.springframework.springrecipeapp.command.IngredientCommand;
import guru.springframework.springrecipeapp.command.RecipeCommand;
import guru.springframework.springrecipeapp.command.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.model.Ingredient;
import guru.springframework.springrecipeapp.model.UnitOfMeasure;
import guru.springframework.springrecipeapp.service.IngredientService;
import guru.springframework.springrecipeapp.service.RecipeService;
import guru.springframework.springrecipeapp.service.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;
    @Mock
    IngredientService ingredientService;

    @InjectMocks
    IngredientController ingredientController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredient"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void testShowIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        when(ingredientService.findCommandByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        Set<UnitOfMeasureCommand> unitOfMeasureCommands = new HashSet<>();
        UnitOfMeasureCommand uom1 = new UnitOfMeasureCommand();
        uom1.setId(1L);
        UnitOfMeasureCommand uom2 = new UnitOfMeasureCommand();
        uom2.setId(2L);
        unitOfMeasureCommands.add(uom1);
        unitOfMeasureCommands.add(uom2);

        when(ingredientService.findCommandByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.findAll()).thenReturn(unitOfMeasureCommands);

        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/add"))
                .andExpect(model().attributeExists("unitsOfMeasure"))
                .andExpect(model().attributeExists("ingredient"));

        verify(ingredientService, times(1)).findCommandByRecipeIdAndIngredientId(anyLong(), anyLong());
        verify(unitOfMeasureService, times(1)).findAll();
    }

    @Test
    public void testShowCreateForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        //when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/add"))
                .andExpect(model().attributeExists(("ingredient")))
                .andExpect(model().attributeExists("unitsOfMeasure"));
    }

    @Test
    public void testCreateOrUpdateIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);
        ingredientCommand.setRecipeId(1L);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

        mockMvc.perform(post("/recipe/1/ingredient/3")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredient/3/show"));
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        mockMvc.perform(get("/recipe/2/ingredient/3/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient"));
        verify(ingredientService, times(1)).deleteIngredientById(anyLong(), anyLong());
    }

}