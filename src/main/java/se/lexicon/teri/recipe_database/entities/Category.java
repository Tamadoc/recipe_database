package se.lexicon.teri.recipe_database.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(nullable = false)
    private String category;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "category_to_recipe",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<Recipe> recipes;

    // Constructors
    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Category(String category, List<Recipe> recipes) {
        this.category = category;
        this.recipes = recipes;
    }

    // Getters and setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int recipeCategoryId) {
        this.categoryId = recipeCategoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, category, recipes);
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
        Category that = (Category) o;
        return categoryId == that.categoryId && Objects.equals(category, that.category) && Objects.equals(recipes, that.recipes);
    }

    // Convenience methods
    public void addRecipe(Recipe recipe) {
        if (recipes == null) {
            recipes = new ArrayList<>();
        }
        if (recipe == null) {
            throw new IllegalArgumentException("recipe is null");
        }

        recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        if (recipes == null) {
            recipes = new ArrayList<>();
        }
        if (recipe == null) {
            throw new IllegalArgumentException("recipe is null");
        }

        recipes.remove(recipe);
    }
}
