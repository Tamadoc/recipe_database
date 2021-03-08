package se.lexicon.teri.recipe_database.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientId;

    @Column(nullable = false, length = 100)
    private String name;

    // Constructors
    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(int id, String name) {
        this.ingredientId = id;
        this.name = name;
    }

    // Getters and setters
    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int id) {
        this.ingredientId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return ingredientId == that.ingredientId && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, name);
    }
}
