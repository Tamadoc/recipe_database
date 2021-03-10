package se.lexicon.teri.recipe_database.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.teri.recipe_database.entities.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    /*
    saveAll
    findById
    existById
    findAll
    deleteById
    delete
    deleteAll
    */

    Optional<Ingredient> findByIngredientNameContainsIgnoreCase(String ingredientName);
}
