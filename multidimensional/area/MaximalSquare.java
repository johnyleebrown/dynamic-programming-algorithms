package dp.multidimensional.area;

/**
 * 221
 *
 * ======
 *
 * Task.
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaximalSquare {
    public static class Solution {
        public int maximalSquare(char[][] ar) {
            int n = ar.length;
            if (n == 0) return 0;
            int m = ar[0].length;
            if (m == 0) return 0;
            int[][] dp = new int[n][m];
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i > 0 && j > 0 && ar[i][j] == '1') {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    } else {
                        dp[i][j] = ar[i][j] == '1' ? 1 : 0;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
            return ans * ans;
        }
    }
}