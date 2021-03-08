package se.lexicon.teri.recipe_database.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(nullable = false, length = 50)
    private String category;

    @ManyToMany
    private List<Recipe> recipeList;

    // Constructors
    public RecipeCategory() {
    }

    public RecipeCategory(String category, List<Recipe> recipeList) {
        this.category = category;
        this.recipeList = recipeList;
    }

    public RecipeCategory(int categoryId, String category, List<Recipe> recipeList) {
        this.categoryId = categoryId;
        this.category = category;
        this.recipeList = recipeList;
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

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
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
        RecipeCategory that = (RecipeCategory) o;
        return categoryId == that.categoryId && Objects.equals(category, that.category) && Objects.equals(recipeList, that.recipeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, category, recipeList);
    }
}
