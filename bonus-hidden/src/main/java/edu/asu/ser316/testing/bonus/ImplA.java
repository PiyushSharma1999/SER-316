package edu.asu.ser316.testing.bonus;

/** Defect: exactly 3 years earns 50% (uses >= instead of >). */
class ImplA implements BonusCalculator {
    @Override
    public int computeBonus(int numYears) throws InvalidData {
        if (numYears <= 0 || numYears >= 70) throw new InvalidData("years out of range: " + numYears);
        if (numYears > 8) return 100;
        if (numYears > 5) return 75;
        if (numYears >= 3) return 50;
        return 0;
    }
}
