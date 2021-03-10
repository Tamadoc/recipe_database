package se.lexicon.teri.recipe_database.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeTest {

    Recipe testObject;

    @BeforeEach
    void setUp() {
        RecipeInstruction instruction = new RecipeInstruction("Put everything in pot and boil.");
        List<RecipeIngredient> ingredients = new ArrayList<>();
        List<RecipeCategory> categories = new ArrayList<>();
        testObject = new Recipe("Irish Stew", instruction, ingredients,  categories);
    }

    @Test
    void addRecipeIngredient() {
        Ingredient potato = new Ingredient("Potatoes");
        Ingredient meat = new Ingredient("Meat");
        Ingredient stock = new Ingredient("Stock");
        testObject.addRecipeIngredient(new RecipeIngredient(potato, 1,Measurement.KG, testObject));
        testObject.addRecipeIngredient(new RecipeIngredient(meat, 1,Measurement.KG, testObject));
        testObject.addRecipeIngredient(new RecipeIngredient(stock, 1,Measurement.KG, testObject));

        long length = testObject.getRecipeIngredients().size();
        assertEquals(3, length);
    }

    @Test
    void removeRecipeIngredient() {
        // set up
        Ingredient potato = new Ingredient("Potatoes");
        Ingredient meat = new Ingredient("Meat");
        Ingredient stock = new Ingredient("Stock");
        testObject.addRecipeIngredient(new RecipeIngredient(potato, 1,Measurement.KG, testObject));
        testObject.addRecipeIngredient(new RecipeIngredient(meat, 1,Measurement.KG, testObject));
        testObject.addRecipeIngredient(new RecipeIngredient(stock, 1,Measurement.KG, testObject));

        // test
        testObject.removeRecipeIngredient(testObject.getRecipeIngredients().get(1));
        long length = testObject.getRecipeIngredients().size();
        assertEquals(2, length);
    }

    @Test
    void addCategory() {
        RecipeCategory breakfast = new RecipeCategory("Breakfast", new ArrayList<>());
        RecipeCategory dinner = new RecipeCategory("Dinner", new ArrayList<>());
        testObject.addCategory(breakfast);
        testObject.addCategory(dinner);

        long length = testObject.getCategories().size();
        assertEquals(2, length);

    }

    @Test
    void removeCategory() {
        // set up
        RecipeCategory breakfast = new RecipeCategory("Breakfast", new ArrayList<>());
        RecipeCategory dinner = new RecipeCategory("Dinner", new ArrayList<>());
        testObject.addCategory(breakfast);
        testObject.addCategory(dinner);

        // test
        testObject.removeCategory(breakfast);
        long length = testObject.getCategories().size();
        assertEquals(1, length);
    }
}