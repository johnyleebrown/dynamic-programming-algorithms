package dp.multidimensional;

/**
 * 63
 */
public class UniquePathsII {
	public static class Solution {
		public int uniquePathsWithObstacles(int[][] ar) {
			int n = ar.length;
			int m = ar[0].length;
			return dfs(n, m, getBase(n, m, ar), ar);
		}

		private int dfs(int i, int j, Integer[][] dp, int[][] ar) {
			if (dp[i][j] != null) {
				return dp[i][j];
			}
			if (ar[i - 1][j - 1] == 1) {
				return 0;
			}

			dp[i][j] = dfs(i - 1, j, dp, ar) + dfs(i, j - 1, dp, ar);
			return dp[i][j];
		}

		/**
		 * Setting base states. previously i=0 and j=0 were 1s since we can have
		 * only 1 unique path to there - base case. with obstacles thought we
		 * make sure that if we encounter 1, we can't get past it, so we set
		 * other cells to 0.
		 */
		private Integer[][] getBase(int n, int m, int[][] ar) {
			Integer[][] dp = new Integer[n + 1][m + 1];

			boolean encounteredObstacle = false;
			for (int i = 1; i <= n; i++) {
				if (ar[i - 1][0] == 1 || encounteredObstacle) {
					dp[i][1] = 0;
					encounteredObstacle = true;
				}
				else {
					dp[i][1] = 1;
				}
			}
			encounteredObstacle = false;
			for (int j = 1; j <= m; j++) {
				if (ar[0][j - 1] == 1 || encounteredObstacle) {
					dp[1][j] = 0;
					encounteredObstacle = true;
				}
				else {
					dp[1][j] = 1;
				}
			}

			return dp;
		}
	}
}
