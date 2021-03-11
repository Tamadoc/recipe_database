package se.lexicon.teri.recipe_database.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.teri.recipe_database.entities.RecipeCategory;

public interface RecipeCategoryRepository extends CrudRepository<RecipeCategory, Integer> {
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
