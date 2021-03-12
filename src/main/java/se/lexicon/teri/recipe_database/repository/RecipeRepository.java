package se.lexicon.teri.recipe_database.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.teri.recipe_database.entities.Recipe;
import se.lexicon.teri.recipe_database.entities.Category;

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
    Optional<Recipe> findByTitleContainsIgnoreCase(String recipeName);

    // Search for recipes containing a specific ingredient by ingredient name
    List<Recipe> findByIngredients_Ingredient_NameContainsIgnoreCase(String ingredientName);

    // Search for recipes that match one or more categories
    List<Recipe> findByCategoriesIn(List<Category> categoryList);

    // Search for recipes in a specific category
    List<Recipe> findByCategories_categoryContainsIgnoreCase(String category);

}
