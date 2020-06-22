package dp.fullSum;

import java.util.Arrays;

/**
 * 64
 */
public class MinimumPathSum {
    /**
     * dp[][]=min sum from 0,0 to i,j
     *
     * how ends=take this cell or not
     */
    public static class Solution {
        public int minPathSum(int[][] a) {
            int n = a.length;
            int m = a[0].length;
            int[][] dp = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], -1);
            }
            return dfs(n, m, a, dp);
        }

        private int dfs(int i, int j, int[][] a, int[][] dp) {
            if (i == 1 && j == 1) {
                return a[i - 1][j - 1];
            }
            if (i == 1) {
                return dfs(i, j - 1, a, dp) + a[i - 1][j - 1];
            }
            if (j == 1) {
                return dfs(i - 1, j, a, dp) + a[i - 1][j - 1];
            }
            if (dp[i][j] != -1) {
                return dp[i][j];
            }
            int ans = Math.min(dfs(i - 1, j, a, dp), dfs(i, j - 1, a, dp)) + a[i - 1][j - 1];
            dp[i][j] = ans;
            return dp[i][j];
        }
    }
}
