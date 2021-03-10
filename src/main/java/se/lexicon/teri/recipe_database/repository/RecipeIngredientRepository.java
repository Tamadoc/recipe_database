package se.lexicon.teri.recipe_database.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.teri.recipe_database.entities.RecipeIngredient;

import java.util.UUID;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, UUID> {
    /*
    saveAll
    findById
    existById
    findAll
    deleteById
    delete
    deleteAll
    */
}
