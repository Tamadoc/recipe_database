package se.lexicon.teri.recipe_database.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.teri.recipe_database.entities.Category;
import se.lexicon.teri.recipe_database.entities.Recipe;

import java.util.List;

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
    List<Recipe> findByTitleContainsIgnoreCase(String recipeName);

    // Search for recipes containing a specific ingredient by ingredientName
    List<Recipe> findByIngredients_Ingredient_NameIgnoreCase(String ingredientName);

    // Search for recipes that match one or more categories
    List<Recipe> findByCategoriesIn(List<Category> categoryList);

    // Search for recipes in a specific category
    List<Recipe> findByCategories_categoryIgnoreCase(String category);

}
