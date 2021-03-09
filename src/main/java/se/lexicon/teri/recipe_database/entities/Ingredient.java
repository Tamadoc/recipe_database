package se.lexicon.teri.recipe_database.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientId;

    @Column(nullable = false)
    private String ingredientName;

    // Constructors
    public Ingredient() {
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    // Getters and setters
    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int id) {
        this.ingredientId = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String name) {
        this.ingredientName = name;
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
        Ingredient that = (Ingredient) o;
        return ingredientId == that.ingredientId && ingredientName.equals(that.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, ingredientName);
    }
}
