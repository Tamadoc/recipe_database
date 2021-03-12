package se.lexicon.teri.recipe_database.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Instructions {

    @Id
    @GeneratedValue
    private UUID instructionId;

    @Column(nullable = false, length = 1500)
    private String text;

    // Constructors
    public Instructions() {
    }

    public Instructions(String text) {
        this.text = text;
    }

    // Getters and setters
    public UUID getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(UUID instructionId) {
        this.instructionId = instructionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        Instructions that = (Instructions) o;
        return Objects.equals(instructionId, that.instructionId) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructionId, text);
    }
}
