package dp.linear.back;

/**
 * 256
 */
public class PaintHouse {
	public static int Solution(int[][] costs) {
		int prev = -1;
		int n = costs.length;
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int cur = 0;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < 3; j++) {
				if (j == prev) continue;
				if (min > costs[i - 1][j]) {
					min = costs[i - 1][j];
					cur = j;
				}
			}
			prev = cur;
			dp[i] = min + dp[i - 1];
		}
		return dp[n];
	}
}
