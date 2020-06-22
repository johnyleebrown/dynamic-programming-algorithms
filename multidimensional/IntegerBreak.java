package dp.multidimensional;

/**
 * 343
 */
public class IntegerBreak {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    public static class Solution1 {
        public static int solution(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    int a = Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]);
                    dp[i] = Math.max(dp[i], a);
                }
            }
            return dp[n];
        }
    }

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    public static class Solution2 {
        public static int solution(int n) {
            int[] memo = new int[n + 2];
            if (n == 2) return 1;
            if (n == 3) return 2;
            memo[1] = 1;
            for (int i = 4; i <= n + 1; i++)
                for (int j = 1; j < i; j++)
                    memo[i] = Math.max(memo[i], j * memo[i - j]);
            return memo[n + 1];
        }
    }
}
