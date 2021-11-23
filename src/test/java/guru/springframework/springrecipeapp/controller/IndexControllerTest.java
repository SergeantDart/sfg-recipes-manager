package guru.springframework.springrecipeapp.controller;

import guru.springframework.springrecipeapp.model.Recipe;
import guru.springframework.springrecipeapp.service.RecipeService;
import guru.springframework.springrecipeapp.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.*;

class IndexControllerTest {

   IndexController indexController;

   @Mock
   RecipeService recipeService;
   @Mock
   Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }
    @Test
    public void getIndexPage() throws Exception {
        Set<Recipe> recipesData = new HashSet<>();
        Recipe r1 = new Recipe();
        r1.setId(1L);
        Recipe r2 = new Recipe();
        r2.setId(2L);
        recipesData.add(r1);
        recipesData.add(r2);

        when(recipeService.getRecipes()).thenReturn(recipesData);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass((Set.class));

        String viewName = indexController.getIndexPage(model);
        assertEquals("index", viewName);

        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        Set<Recipe> capturedRecipes = argumentCaptor.getValue();

        assertEquals(2, capturedRecipes.size());
    }
}