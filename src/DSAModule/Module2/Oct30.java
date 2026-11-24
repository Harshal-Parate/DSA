package DSAModule.Module2;

import java.util.Arrays;

public class Oct30 {

    private long paint(int k, int[] A) {
        long lower = 0;
        long upper = 0;

        // Find minimum and maximum possible time.
        for (int a : A) {
            upper += a;
            lower = Math.max(lower, a);
        }

        long ans = upper;

        // Binary search for minimum valid time.
        while (lower <= upper) {
            long mid = lower + (upper - lower) / 2;

            if (isValidTime(mid, A, k)) {
                ans = mid;
                upper = mid - 1; // Try smaller time.
            } else {
                lower = mid + 1; // Need more time.
            }
        }
        return ans;
    }

    private boolean isValidTime(long timeBudget, int[] A, int maxPainters) {

        long currentTime = timeBudget;
        int painters = 1;

        // Allocate consecutive boards greedily.
        for (int a : A) {

            if (a > timeBudget) return false;

            // Assign next board to a new painter.
            if (currentTime < a) {
                painters++;
                currentTime = timeBudget;
            }

            currentTime -= a;
        }

        // Check if available painters are sufficient.
        return painters <= maxPainters;
    }

    public int aggressiveCows(int[] stallPositions, int totalCows) {
        Arrays.sort(stallPositions);

        int minDistance = 1;
        int maxDistance = stallPositions[stallPositions.length - 1] - stallPositions[0];
        int maximumMinimumDistance = 0;

        while (minDistance <= maxDistance) {
            int currentDistance = minDistance + (maxDistance - minDistance) / 2;

            if (canPlaceCows(stallPositions, currentDistance, totalCows)) {
                maximumMinimumDistance = currentDistance;
                minDistance = currentDistance + 1;
            } else {
                maxDistance = currentDistance - 1;
            }
        }

        return maximumMinimumDistance;
    }

    private boolean canPlaceCows(int[] stallPositions, int minimumDistance, int totalCows) {
        int cowsPlaced = 1;
        int lastPlacedPosition = stallPositions[0];

        for (int i = 1; i < stallPositions.length; i++) {
            if (stallPositions[i] - lastPlacedPosition >= minimumDistance) {
                cowsPlaced++;
                lastPlacedPosition = stallPositions[i];
            }
        }

        return cowsPlaced >= totalCows;
    }
}
