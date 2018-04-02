/**
 * 53
 * Find the contiguous subarray within an array
 * (containing at least one number) which has the largest sum.
 * and return the sum
 */
public class MaximumSubarray {
    /**
     * Kadane's Algorithm
     * O(n), O(1)
     *
     * if we know the maximum subarray sum ending at position i
     * what is the maximum subarray sum ending at position i+1
     * either the maximum subarray sum ending at position i+1
     * includes the maximum subarray sum ending at position i as a prefix, or it doesn't
     */
    public static int solution1(int[] A) {
        int maxSoFar = A[0];
        int maxEndingHere = A[0];
        for (int i = 1; i < A.length; ++i) {
            maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public static int solution2(int[] A) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (sum < 0) sum = A[i];
            else sum += A[i];
            if (sum > max) max = sum;
        }
        return max;
    }
}
