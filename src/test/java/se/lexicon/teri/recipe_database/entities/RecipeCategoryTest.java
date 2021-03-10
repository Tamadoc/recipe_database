package se.lexicon.teri.recipe_database.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCategoryTest {

    RecipeCategory testObject;
    Recipe recipe1, recipe2;

    @BeforeEach
    void setUp() {
        RecipeInstruction instruction1 = new RecipeInstruction("Put everything in pot and boil.");
        RecipeInstruction instruction2 = new RecipeInstruction("Stir-fry everything together.");
        List<RecipeIngredient> ingredients = new ArrayList<>();
        List<RecipeCategory> categories = new ArrayList<>();
        recipe1  = new Recipe("Irish Stew", instruction1, ingredients,  categories);
        recipe2  = new Recipe("Stir-fried Chicken", instruction1, ingredients,  categories);

        testObject = new RecipeCategory("Dinner", new ArrayList<>());
    }

    @Test
    void addRecipe() {
        testObject.addRecipe(recipe1);
        testObject.addRecipe(recipe2);

        long length = testObject.getRecipes().size();
        assertEquals(2, length);
    }

    @Test
    void removeRecipe() {
        // set up
        testObject.addRecipe(recipe1);
        testObject.addRecipe(recipe2);

        //test
        testObject.removeRecipe(recipe2);
        long length = testObject.getRecipes().size();
        assertEquals(1, length);
    }
}