package edu.asu.ser316.testing.bonus;

/**
 * Requirement given to students (spec only, no code):
 * Employees who have worked more than 3 years get a bonus of 50% of their salary.
 * More than 5 years earns 75%. More than 8 years earns 100%.
 * The number of years must be positive and less than 70,
 * otherwise InvalidData is thrown.
 *
 * computeBonus returns the bonus as a percentage: 0, 50, 75 or 100.
 */
public interface BonusCalculator {

    int computeBonus(int numYears) throws InvalidData;

    /**
     * Returns implementation A, B or C. Tests pick one with the
     * system property bonus.impl (default C).
     */
    static BonusCalculator forName(String name) {
        switch (name.trim().toUpperCase()) {
            case "A": return new ImplA();
            case "B": return new ImplB();
            case "C": return new ImplC();
            default: throw new IllegalArgumentException("Unknown implementation: " + name);
        }
    }
}
