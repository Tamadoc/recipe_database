package se.lexicon.teri.recipe_database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.teri.recipe_database.entities.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository repository;

    private Recipe irishStew;
    private List<Category> categoryList;

    @BeforeEach
    void setUp() {
        Ingredient potato = new Ingredient("Potato");
        Ingredient meat = new Ingredient("Meat");
        Ingredient stock = new Ingredient("Stock");
        Category dinner = new Category("Dinner");

        irishStew = new Recipe();
        irishStew.setTitle("Irish Stew");
        irishStew.setInstructions("Put everything in a pot and boil");
        irishStew.addRecipeIngredient(new Quantity(1, Measurement.KG, potato));
        irishStew.addRecipeIngredient(new Quantity(200, Measurement.G, meat));
        irishStew.addRecipeIngredient(new Quantity(3, Measurement.L, stock));
        irishStew.addCategory(dinner);
        repository.save(irishStew);

        categoryList = new ArrayList<>();
        categoryList.add(dinner);
    }

    @Test
    void test_findByRecipeNameContainsIgnoreCase() {

        List<Recipe> result = repository.findByTitleContainsIgnoreCase("iri");

        if (!result.isEmpty()) {
            assertTrue(result.contains(irishStew));
        }
    }

    @Test
    void findByRecipeIngredients() {

        List<Recipe> result = repository.findByIngredients_Ingredient_NameIgnoreCase("pot");

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

        List<Recipe> result = repository.findByCategories_categoryIgnoreCase("din");

        if (!result.isEmpty()) {
            assertTrue(result.contains(irishStew));
        }
    }
}