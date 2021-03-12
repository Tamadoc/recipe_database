package se.lexicon.teri.recipe_database.entities;

public enum Measurement {
    TBSP("Tablespoon"),
    TSP("Teaspoon"),
    KG("Kilogram"),
    G("Gram"),
    L("Litre"),
    DL("Decilitre"),
    ML("Millilitre"),
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large"),
    ITEM("");

    public final String label;

    Measurement(String label) {
        this.label = label;
    }
}
