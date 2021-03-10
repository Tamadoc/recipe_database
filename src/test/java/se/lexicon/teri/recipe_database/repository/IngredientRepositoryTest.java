package se.lexicon.teri.recipe_database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.teri.recipe_database.entities.Ingredient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IngredientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IngredientRepository repository;

    Ingredient testObject;

    @BeforeEach
    void setUp() {
        testObject = new Ingredient("Potato");
    }

    @Test
    void test_findByIngredientNameIgnoreCase() {

        Optional<Ingredient> result = repository.findByIngredientNameContainsIgnoreCase("pot");

        if (result.isPresent()) {
            String expected = testObject.getIngredientName();
            String actual = result.get().getIngredientName();
            assertEquals(expected, actual);
        }
    }
}