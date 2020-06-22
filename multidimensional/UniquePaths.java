package dp.multidimensional;

/**
 * 62
 */
public class UniquePaths {
	public static class Solution {
		public int uniquePaths(int m, int n) {
			return dfs(n, m, new int[n + 1][m + 1]);
		}

		private int dfs(int i, int j, int[][] dp) {
			if (i == 1 || j == 1) {
				return 1;
			}
			if (dp[i][j] != 0) {
				return dp[i][j];
			}

			dp[i][j] = dfs(i - 1, j, dp) + dfs(i, j - 1, dp);
			return dp[i][j];
		}
	}
}
