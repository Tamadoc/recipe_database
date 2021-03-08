package se.lexicon.teri.recipe_database.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    @Column(nullable = false, length = 200)
    private String recipeName;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "instruction_id")
    private RecipeInstruction instructions;

    @OneToMany
    private List<RecipeIngredient> recipeIngredients;

    @ManyToMany
    private List<RecipeCategory> categories;

    // Constructors
    public Recipe() {
    }

    public Recipe(String recipeName, RecipeInstruction instructions, List<RecipeIngredient> recipeIngredients, List<RecipeCategory> categories) {
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.recipeIngredients = recipeIngredients;
        this.categories = categories;
    }

    public Recipe(int recipeId, String recipeName, RecipeInstruction instructions, List<RecipeIngredient> recipeIngredients, List<RecipeCategory> categories) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.recipeIngredients = recipeIngredients;
        this.categories = categories;
    }

    // Getters and setters
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public RecipeInstruction getInstructions() {
        return instructions;
    }

    public void setInstructions(RecipeInstruction instructions) {
        this.instructions = instructions;
    }

    public List<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<RecipeCategory> categories) {
        this.categories = categories;
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
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId && Objects.equals(recipeName, recipe.recipeName) && Objects.equals(recipeIngredients, recipe.recipeIngredients) && Objects.equals(instructions, recipe.instructions) && Objects.equals(categories, recipe.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName, recipeIngredients, instructions, categories);
    }
}
