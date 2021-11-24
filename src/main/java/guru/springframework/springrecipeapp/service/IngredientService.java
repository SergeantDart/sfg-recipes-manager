package guru.springframework.springrecipeapp.service;

import guru.springframework.springrecipeapp.command.IngredientCommand;
import guru.springframework.springrecipeapp.command.RecipeCommand;
import guru.springframework.springrecipeapp.controller.IngredientController;
import guru.springframework.springrecipeapp.model.Ingredient;
import guru.springframework.springrecipeapp.model.Recipe;

public interface IngredientService {

    IngredientCommand findCommandByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteIngredientById(Long ingredientId, Long recipeId);
}
