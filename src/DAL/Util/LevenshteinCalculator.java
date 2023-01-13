package DAL.Util;

public class LevenshteinCalculator {
    public static int distance(String a, String b) {
        return calculate(a, b);
    }

    /**
     * Calculates the Levenshtein Distance of two strings.
     * @param a String #1 (source).
     * @param b String #2 (target).
     * @return The Levenshtein Distance of two strings as an integer.
     */
    private static int calculate(String a, String b) {
        int[] previousRow = new int[b.length() + 1];

        for (int j = 1; j <= b.length(); j++) {
            previousRow[j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            int previousDiagonal = previousRow[0];
            int previousColumn = previousDiagonal++;

            for (int j = 1; j <= b.length(); j++) {
                int insertOrDelete = Math.min(previousColumn, previousRow[j]) + 1;
                int edit = previousDiagonal + (a.charAt(i - 1) != b.charAt(j - 1) ? 1 : 0);

                previousColumn = Math.min(insertOrDelete, edit);
                previousDiagonal = previousRow[j];
                previousRow[j] = previousColumn;
            }
        }

        return previousRow[b.length()];
    }

    public static void main(String[] args) {
        System.out.println(distance("Patrick", "Debbie"));
    }
}
