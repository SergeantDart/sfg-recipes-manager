package guru.springframework.springrecipeapp.service;

import guru.springframework.springrecipeapp.command.RecipeCommand;
import guru.springframework.springrecipeapp.converter.RecipeCommandToRecipe;
import guru.springframework.springrecipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(long l);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
