/**
 * Repetition of items allowed
 */
public class UnboundedKnapsack {
    private int Solution(int[] values, int[] weights, int capacity) {
        int dp[] = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            for (int j = 0; j < values.length; j++) {
                if (weights[j] <= i)
                    dp[i] = Math.max(dp[i], dp[i - weights[j]] + values[j]);
            }
        }
        return dp[capacity];
    }
}
