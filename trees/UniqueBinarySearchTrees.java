package dp.trees;

/**
 * 96
 */
public class UniqueBinarySearchTrees {
    /**
     * Intuition: imagine each of the numbers as a root and compute the
     *            product of dp from elements on the left and on the right
     * Recurrence: dp[i] = sum(dp[j] * dp[i - j - 1]), 0 <= j < i
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    // base
    public static int solution(int n) {
        if (n < 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    // O(n^2/2), O(n)
    public static int solution2(int n) {
        if (n < 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i/2; j++) {
                dp[i] += dp[j] * dp[i - j - 1] * 2;
            }
            if (i % 2 != 0) dp[i] += dp[i/2] * dp[i - i/2 - 1];
        }
        return dp[n];
    }
}
