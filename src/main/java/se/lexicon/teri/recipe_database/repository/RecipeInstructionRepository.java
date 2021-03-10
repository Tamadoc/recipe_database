package se.lexicon.teri.recipe_database.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.teri.recipe_database.entities.RecipeInstruction;

import java.util.UUID;

public interface RecipeInstructionRepository extends CrudRepository<RecipeInstruction, UUID> {
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
