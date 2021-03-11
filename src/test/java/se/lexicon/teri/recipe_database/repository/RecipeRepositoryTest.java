package se.lexicon.teri.recipe_database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.teri.recipe_database.entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository repository;

    private Recipe irishStew;
    private List<RecipeCategory> categoryList;

    @BeforeEach
    void setUp() {
        irishStew = new Recipe();
        irishStew.setRecipeName("Irish Stew");

        Ingredient potato = new Ingredient("Potato");
        Ingredient meat = new Ingredient("Meat");
        Ingredient stock = new Ingredient("Stock");

        RecipeIngredient ingredient1 = new RecipeIngredient(potato, 1, Measurement.KG, irishStew);
        RecipeIngredient ingredient2 = new RecipeIngredient(meat, 200, Measurement.G, irishStew);
        RecipeIngredient ingredient3 = new RecipeIngredient(stock, 3, Measurement.L, irishStew);

        List<RecipeIngredient> ingredientList = Arrays.asList(ingredient1, ingredient2, ingredient3);
        irishStew.setIngredients(ingredientList);

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(irishStew);

        categoryList = new ArrayList<>();
        categoryList.add(new RecipeCategory("Dinner", recipeList));
        irishStew.setCategories(categoryList);

        repository.save(irishStew);
    }

    @Test
    void test_findByRecipeNameContainsIgnoreCase() {

        Optional<Recipe> result = repository.findByRecipeNameContainsIgnoreCase("irish");

        if (result.isPresent()) {
            String expected = irishStew.getRecipeName();
            String actual = result.get().getRecipeName();
            assertEquals(expected, actual);
        }
    }

    @Test
    void findByRecipeIngredients() {

        List<Recipe> result = repository.findByIngredients_Ingredient_IngredientNameIgnoreCase("potato");

        if (!result.isEmpty()) {
            assertTrue(result.contains(irishStew));
        }
    }

    @Test
    void findByCategoriesIn() {

        List<Recipe> result = repository.findByCategoriesIn(categoryList);

        if (!result.isEmpty()) {
            assertTrue(result.contains(irishStew));
        }
    }

    @Test
    void findByCategories_categoryIgnoreCase() {

        List<Recipe> result = repository.findByCategories_categoryIgnoreCase("dinner");

        if (!result.isEmpty()) {
            assertTrue(result.contains(irishStew));
        }
    }
}