package guru.springframework.springrecipeapp.service;

import guru.springframework.springrecipeapp.command.RecipeCommand;
import guru.springframework.springrecipeapp.converter.RecipeCommandToRecipe;
import guru.springframework.springrecipeapp.converter.RecipeToRecipeCommand;
import guru.springframework.springrecipeapp.model.Recipe;
import guru.springframework.springrecipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Recipe service called...");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe not found !");
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(long l) {
        return recipeToRecipeCommand.convert(recipeRepository.findById(l).get());
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved recipeId: " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }


}
