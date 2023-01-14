package DAL.Util;

public class LevenshteinCalculator {
    /**
     * Calculates the Levenshtein Distance between two strings.
     * @param source The source to change.
     * @param target The target for the source to change into.
     * @return The Levenshtein Distance between the source and the target as an integer.
     */
    private static int distance(String source, String target) {
        source = source.toLowerCase();
        target = target.toLowerCase();

        // Keeps track of the previous row in the matrix.
        int[] previousRow = new int[target.length() + 1];

        // Fills out the first row of our matrix against
        // an empty source. E.g. 0 to target.length.
        for (int j = 1; j <= target.length(); j++) {
            previousRow[j] = j;
        }

        // Iterates through the source.
        for (int i = 1; i <= source.length(); i++) {
            // Keeps track of the previous diagonal value
            // to the new field we're calculating.
            int previousDiagonal = previousRow[0];
            // Keeps track of the previous value to
            // the left of the new field we're calculating.
            int previousLeft = previousRow[0]++;

            // Iterates through the target.
            for (int j = 1; j <= target.length(); j++) {
                // Keeps track of how many changes are needed for this field,
                // when using either insertion or deletion.
                int insertOrDelete = Math.min(previousLeft, previousRow[j]) + 1;
                // Keeps track of how many substitutions are needed for this field.
                // If the current character of the source and target are the same,
                // Then it takes the same amount of changes as our previous diagonal.
                int edit = previousDiagonal + (source.charAt(i - 1) != target.charAt(j - 1) ? 1 : 0);


                // Sets the previous left field to be the result of this one
                // (this result will be the previous left of the next iteration of our target).
                previousLeft = Math.min(insertOrDelete, edit);
                // Sets the previous diagonal to be equal to the field above this one
                // (same reasoning as above).
                previousDiagonal = previousRow[j];
                // Updates the field above this one to be equal to this result.
                // This updates the previous row to be equal to the one we're currently calculating,
                // along the way (for use in the next row).
                previousRow[j] = previousLeft;
            }
        }

        // Returns the last index of our previous row,
        // as that will have the Levenshtein Distance at
        // the end of the calculation.
        return previousRow[target.length()];
    }
}
