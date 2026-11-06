package edu.asu.ser316.testing.grades;

/**
 * White-box examples. The code matches the slides, including its bugs.
 */
public class Grades {

    static final float MAX = Float.MAX_VALUE;

    private float[] lastLowestTwo;

    /**
     * Requirement: return the average score after dropping the lowest one.
     *
     * Known bugs, used for loop coverage:
     * an empty array returns 99999.0, and a single score returns NaN.
     */
    public float homeworkAverage(float[] scores) {
        float min = 99999;                        // A
        float total = 0;                          // A
        for (int i = 0; i < scores.length; i++) { // B
            if (scores[i] < min)                  // C
                min = scores[i];                  // D
            total += scores[i];                   // E
        }
        total = total - min;                      // F
        return total / (scores.length - 1);       // F
    }

    /**
     * Requirement: return the average of the scores. If there are at least
     * two scores, also output the two lowest.
     */
    public float findAverage(float[] scores) {
        lastLowestTwo = null;
        float total = 0, min = MAX, min2 = MAX;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] < min) {
                min2 = min;
                min = scores[i];
            } else if (scores[i] < min2) {
                min2 = scores[i];
            }
            total += scores[i];
        }
        if (min != MAX && min2 != MAX)
            output(min, min2);
        return total / scores.length;
    }

    /** The two lowest scores from the last findAverage call, or null if none were output. */
    public float[] getLastLowestTwo() {
        return lastLowestTwo;
    }

    private void output(float lowest, float secondLowest) {
        lastLowestTwo = new float[] {lowest, secondLowest};
        System.out.println("Lowest scores: " + lowest + ", " + secondLowest);
    }
}
