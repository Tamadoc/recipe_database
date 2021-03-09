package se.lexicon.teri.recipe_database.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue
    private UUID recipeIngredientId;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private Measurement measurement;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // Constructors
    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredient, double amount, Measurement measurement, Recipe recipe) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.measurement = measurement;
        this.recipe = recipe;
    }

    // Getters and setters
    public UUID getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(UUID recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeIngredientId, ingredient, amount, measurement, recipe);
    }

    // Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecipeIngredient that = (RecipeIngredient) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(recipeIngredientId, that.recipeIngredientId) && Objects.equals(ingredient, that.ingredient) && measurement == that.measurement && Objects.equals(recipe, that.recipe);
    }
}