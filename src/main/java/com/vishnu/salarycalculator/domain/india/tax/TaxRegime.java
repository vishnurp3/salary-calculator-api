package com.vishnu.salarycalculator.domain.india.tax;

public record TaxRegime(int year, Type type) {

    public enum Type {
        NEW, OLD
    }

    public static TaxRegime newRegimeFor2025() {
        return new TaxRegime(2025, Type.NEW);
    }

    public boolean isNewRegime() {
        return type == Type.NEW;
    }

    public boolean isOldRegime() {
        return type == Type.OLD;
    }

    @Override
    public String toString() {
        return type + "-REGIME-" + year;
    }
}
