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
    private String title;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "instruction_id")
    private Instructions instructions;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "recipe",
            orphanRemoval = true)
    private List<Quantity> ingredients;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "category_to_recipe",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    // Constructors
    public Recipe() {
    }

    public Recipe(String title, Instructions instructions, List<Quantity> ingredients, List<Category> categories) {
        this.title = title;
        this.instructions = instructions;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String recipeName) {
        this.title = recipeName;
    }

    public List<Quantity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Quantity> quantities) {
        this.ingredients = quantities;
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, title, ingredients, instructions, categories);
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
        return recipeId == recipe.recipeId && Objects.equals(title, recipe.title) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(instructions, recipe.instructions) && Objects.equals(categories, recipe.categories);
    }

    // Convenience methods
    public void addRecipeIngredient(Quantity quantity) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        if (quantity == null) {
            throw new IllegalArgumentException("recipeIngredient is null");
        }

        ingredients.add(quantity);    // Adds ingredient to List<T>
        quantity.setRecipe(this);           // Sets recipe field in RecipeIngredient
    }

    public void removeRecipeIngredient(Quantity quantity) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        if (quantity == null) {
            throw new IllegalArgumentException("recipeIngredient is null");
        }

        ingredients.remove(quantity); // Removes ingredient from List<T>
        quantity.setRecipe(null);
    }

    public void addCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        if (category == null) {
            throw new IllegalArgumentException("category is null");
        }

        categories.add(category);             // Adds category to List<T>
        category.addRecipe(this);             // Adds recipe to List<T> in RecipeCategory
    }

    public void removeCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        if (category == null) {
            throw new IllegalArgumentException("category is null");
        }

        categories.remove(category);          // Removes category from List<T>
        category.removeRecipe(this);          // Removes recipe from List<T> in RecipeCategory
    }
}
