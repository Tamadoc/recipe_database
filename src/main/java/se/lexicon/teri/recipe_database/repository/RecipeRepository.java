package se.lexicon.teri.recipe_database.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.teri.recipe_database.entities.Ingredient;
import se.lexicon.teri.recipe_database.entities.Recipe;
import se.lexicon.teri.recipe_database.entities.RecipeCategory;
import se.lexicon.teri.recipe_database.entities.RecipeIngredient;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    /*
    saveAll
    findById
    existById
    findAll
    deleteById
    delete
    deleteAll
    */

    // Search for recipes by name
    Optional<Recipe> findByRecipeNameContainsIgnoreCase(String recipeName);

    // Search for recipes containing a specific ingredient by ingredientName
    List<Recipe> findByIngredients_Ingredient_IngredientNameIgnoreCase(String ingredientName);

    // Search for recipes that match one or more categories
    List<Recipe> findByCategoriesIn(List<RecipeCategory> categoryList);

    // Search for recipes in a specific category
    List<Recipe> findByCategories_categoryIgnoreCase(String category);

}
