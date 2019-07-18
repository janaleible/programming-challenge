package de.exxcellent.challenge.analysis;

public class ColumnNotFoundException extends Exception {

    private String invalidColumnName;

    public ColumnNotFoundException(String invalidColumnName) {
        this.invalidColumnName = invalidColumnName;
    }

    public String getInvalidColumnName() {
        return invalidColumnName;
    }
}
