package se.lexicon.teri.recipe_database.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class RecipeInstruction {

    @Id
    @GeneratedValue
    private UUID instructionId;

    @Column(nullable = false, length = 1500)
    private String instructions;

    // Constructors
    public RecipeInstruction() {
    }

    public RecipeInstruction(String instructions) {
        this.instructions = instructions;
    }

    // Getters and setters
    public UUID getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(UUID instructionId) {
        this.instructionId = instructionId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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
        RecipeInstruction that = (RecipeInstruction) o;
        return Objects.equals(instructionId, that.instructionId) && Objects.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructionId, instructions);
    }
}
