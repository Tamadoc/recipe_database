package se.lexicon.teri.recipe_database.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    @Column(nullable = false)
    private String recipeName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "instruction_id")
    private RecipeInstruction recipeInstructions;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "recipe",
            orphanRemoval = true)
    private List<RecipeIngredient> ingredients;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "category_to_recipe",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<RecipeCategory> categories;

    // Constructors
    public Recipe() {
    }

    public Recipe(String recipeName, RecipeInstruction recipeInstructions, List<RecipeIngredient> ingredients, List<RecipeCategory> categories) {
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
        this.ingredients = ingredients;
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

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> recipeIngredients) {
        this.ingredients = recipeIngredients;
    }

    public RecipeInstruction getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(RecipeInstruction instructions) {
        this.recipeInstructions = instructions;
    }

    public List<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<RecipeCategory> categories) {
        this.categories = categories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName, ingredients, recipeInstructions, categories);
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
        return recipeId == recipe.recipeId && Objects.equals(recipeName, recipe.recipeName) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(recipeInstructions, recipe.recipeInstructions) && Objects.equals(categories, recipe.categories);
    }

    // Convenience methods
    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        if (recipeIngredient == null) {
            throw new IllegalArgumentException("recipeIngredient is null");
        }

        ingredients.add(recipeIngredient);    // Adds ingredient to List<T>
        recipeIngredient.setRecipe(this);           // Sets recipe field in RecipeIngredient
    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        if (recipeIngredient == null) {
            throw new IllegalArgumentException("recipeIngredient is null");
        }

        ingredients.remove(recipeIngredient); // Removes ingredient from List<T>
        recipeIngredient.setRecipe(null);
    }

    public void addCategory(RecipeCategory recipeCategory) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        if (recipeCategory == null) {
            throw new IllegalArgumentException("category is null");
        }

        categories.add(recipeCategory);             // Adds category to List<T>
        recipeCategory.addRecipe(this);             // Adds recipe to List<T> in RecipeCategory
    }

    public void removeCategory(RecipeCategory recipeCategory) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        if (recipeCategory == null) {
            throw new IllegalArgumentException("category is null");
        }

        categories.remove(recipeCategory);          // Removes category from List<T>
        recipeCategory.removeRecipe(this);          // Removes recipe from List<T> in RecipeCategory
    }
}
