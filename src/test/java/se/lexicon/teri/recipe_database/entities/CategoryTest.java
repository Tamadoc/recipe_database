package se.lexicon.teri.recipe_database.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category testCategory;
    Recipe recipe1, recipe2;

    @BeforeEach
    void setUp() {
        recipe1 = new Recipe("Irish Stew", "Put everything in pot and boil.");
        recipe2 = new Recipe("Stir-fried Chicken", "Stir-fry everything together.");

        testCategory = new Category("Dinner");
    }

    @Test
    void addRecipe() {
        testCategory.addRecipe(recipe1);
        testCategory.addRecipe(recipe2);

        long length = testCategory.getRecipes().size();
        assertEquals(2, length);
    }

    @Test
    void removeRecipe() {
        // set up
        testCategory.addRecipe(recipe1);
        testCategory.addRecipe(recipe2);

        //test
        testCategory.removeRecipe(recipe2);
        long length = testCategory.getRecipes().size();
        assertEquals(1, length);
    }
}