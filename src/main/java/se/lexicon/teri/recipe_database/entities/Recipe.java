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

    @Column
    private String instructions;

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

    public Recipe(String title, String instructions) {
        this.title = title;
        this.instructions = instructions;
    }

    public Recipe(String title, String instructions, List<Quantity> ingredients, List<Category> categories) {
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Quantity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Quantity> quantities) {
        this.ingredients = quantities;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    // Overrides
    @Override
    public int hashCode() {
        return Objects.hash(recipeId, title, instructions, ingredients, categories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId && Objects.equals(title, recipe.title) && Objects.equals(instructions, recipe.instructions) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(categories, recipe.categories);
    }

    // Convenience methods
    public void addRecipeIngredient(Quantity quantity) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        if (quantity == null) {
            throw new IllegalArgumentException("recipeIngredient is null");
        }

        ingredients.add(quantity);
        quantity.setRecipe(this);
    }

    public void removeRecipeIngredient(Quantity quantity) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        if (quantity == null) {
            throw new IllegalArgumentException("recipeIngredient is null");
        }

        ingredients.remove(quantity);
        quantity.setRecipe(null);
    }

    public void addCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        if (category == null) {
            throw new IllegalArgumentException("category is null");
        }

        categories.add(category);
        category.addRecipe(this);
    }

    public void removeCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        if (category == null) {
            throw new IllegalArgumentException("category is null");
        }

        categories.remove(category);
        category.removeRecipe(this);
    }
}