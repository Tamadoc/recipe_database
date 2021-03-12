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
        Instructions instruction = new Instructions("Put everything in pot and boil.");
        List<Quantity> ingredients = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        testObject = new Recipe("Irish Stew", instruction, ingredients,  categories);
    }

    @Test
    void addRecipeIngredient() {
        Ingredient potato = new Ingredient("Potatoes");
        Ingredient meat = new Ingredient("Meat");
        Ingredient stock = new Ingredient("Stock");
        testObject.addRecipeIngredient(new Quantity(1, Measurement.KG, potato, testObject));
        testObject.addRecipeIngredient(new Quantity(250, Measurement.G, meat, testObject));
        testObject.addRecipeIngredient(new Quantity(1, Measurement.L, stock, testObject));

        long length = testObject.getIngredients().size();
        assertEquals(3, length);
    }

    @Test
    void removeRecipeIngredient() {
        // set up
        Ingredient potato = new Ingredient("Potatoes");
        Ingredient meat = new Ingredient("Meat");
        Ingredient stock = new Ingredient("Stock");
        testObject.addRecipeIngredient(new Quantity(1, Measurement.KG, potato, testObject));
        testObject.addRecipeIngredient(new Quantity(250, Measurement.G, meat, testObject));
        testObject.addRecipeIngredient(new Quantity(1, Measurement.L, stock, testObject));

        // test
        testObject.removeRecipeIngredient(testObject.getIngredients().get(1));
        long length = testObject.getIngredients().size();
        assertEquals(2, length);
    }

    @Test
    void addCategory() {
        Category breakfast = new Category("Breakfast", new ArrayList<>());
        Category dinner = new Category("Dinner", new ArrayList<>());
        testObject.addCategory(breakfast);
        testObject.addCategory(dinner);

        long length = testObject.getCategories().size();
        assertEquals(2, length);

    }

    @Test
    void removeCategory() {
        // set up
        Category breakfast = new Category("Breakfast", new ArrayList<>());
        Category dinner = new Category("Dinner", new ArrayList<>());
        testObject.addCategory(breakfast);
        testObject.addCategory(dinner);

        // test
        testObject.removeCategory(breakfast);
        long length = testObject.getCategories().size();
        assertEquals(1, length);
    }
}