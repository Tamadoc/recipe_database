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
    private List<Category> categoryList;

    @BeforeEach
    void setUp() {
        irishStew = new Recipe();
        irishStew.setTitle("Irish Stew");

        Ingredient potato = new Ingredient("Potato");
        Ingredient meat = new Ingredient("Meat");
        Ingredient stock = new Ingredient("Stock");

        Quantity ingredient1 = new Quantity(1, Measurement.KG, potato, irishStew);
        Quantity ingredient2 = new Quantity(250, Measurement.G, meat, irishStew);
        Quantity ingredient3 = new Quantity(1, Measurement.L, stock, irishStew);

        List<Quantity> ingredientList = Arrays.asList(ingredient1, ingredient2, ingredient3);
        irishStew.setIngredients(ingredientList);

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(irishStew);

        categoryList = new ArrayList<>();
        categoryList.add(new Category("Dinner", recipeList));
        irishStew.setCategories(categoryList);

        repository.save(irishStew);
    }

    @Test
    void test_findByRecipeTitle() {

        Optional<Recipe> result = repository.findByTitleContainsIgnoreCase("irish");

        if (result.isPresent()) {
            String expected = irishStew.getTitle();
            String actual = result.get().getTitle();
            assertEquals(expected, actual);
        }
    }

    @Test
    void test_findByIngredient() {

        List<Recipe> result = repository.findByIngredients_Ingredient_NameContainsIgnoreCase("pot");

        if (!result.isEmpty()) {
            assertTrue(result.contains(irishStew));
        }
    }

    @Test
    void test_findByCategories() {

        List<Recipe> result = repository.findByCategoriesIn(categoryList);

        if (!result.isEmpty()) {
            assertTrue(result.contains(irishStew));
        }
    }

    @Test
    void test_findByCategory() {

        List<Recipe> result = repository.findByCategories_categoryContainsIgnoreCase("din");

        if (!result.isEmpty()) {
            assertTrue(result.contains(irishStew));
        }
    }
}