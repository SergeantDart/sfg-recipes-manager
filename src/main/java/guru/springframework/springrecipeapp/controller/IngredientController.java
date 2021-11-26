package guru.springframework.springrecipeapp.controller;

import guru.springframework.springrecipeapp.command.IngredientCommand;
import guru.springframework.springrecipeapp.command.RecipeCommand;
import guru.springframework.springrecipeapp.command.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.model.Ingredient;
import guru.springframework.springrecipeapp.service.IngredientService;
import guru.springframework.springrecipeapp.service.RecipeService;
import guru.springframework.springrecipeapp.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{id}/ingredient")
    public String listRecipeIngredients(@PathVariable String id, Model model) {
        log.debug("Getting ingredients list for recipe id: " + id);
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }


    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug("Getting ingredients list for recipe id: " + recipeId);

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        model.addAttribute("ingredient", ingredientService.findCommandByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String ingredientUpdateForm(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug("Update form for ingredient id: " + ingredientId + ", for recipe id: " + recipeId);

        model.addAttribute("unitsOfMeasure", unitOfMeasureService.findAll());
        model.addAttribute("ingredient", ingredientService.findCommandByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        return "recipe/ingredient/add";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String ingredientCreateForm(@PathVariable String recipeId, Model model) {
        log.debug("Create a new ingredient for " + recipeId);

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("unitsOfMeasure", unitOfMeasureService.findAll());
        model.addAttribute("ingredient", ingredientCommand);

        return "recipe/ingredient/add";
    }

    @PostMapping("/recipe/{recipeId}/ingredient/{ingredientId}")
    public String saveOrUpdateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, @ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + savedIngredientCommand.getRecipeId() + "/ingredient/" + savedIngredientCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, @ModelAttribute IngredientCommand ingredientCommand) {
        ingredientService.deleteIngredientById(Long.valueOf(ingredientId), Long.valueOf(recipeId));
        return "redirect:/recipe/" + recipeId + "/ingredient";
    }



}
