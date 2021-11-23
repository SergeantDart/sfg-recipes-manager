package guru.springframework.springrecipeapp.controller;

import guru.springframework.springrecipeapp.model.Category;
import guru.springframework.springrecipeapp.model.UnitOfMeasure;
import guru.springframework.springrecipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug("Getting index page...");
        return "index";
    }
}
