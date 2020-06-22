package dp.fullSum;

import java.util.Arrays;

/**
 * 518
 */
public class CoinChange2 {
	/**
	 * Similarly to knapsack problem we use 2d array to store number of
	 * combinations for each coin for each change amount
	 */
	public static class Solution {
		public int change(int amount, int[] coins) {
			int n = coins.length;
			int[][] dp = new int[amount + 1][n + 1];
			Arrays.fill(dp[0], 1); // we can cover 0 amount in 1 combination
			for (int i = 1; i <= amount; i++) {
				for (int j = 1; j <= n; j++) {
					if (i > coins[j - 1]) { // we can this coin to (cur amount - nominal of cur coin) combination
						dp[i][j] = dp[i - coins[j - 1]][j] + dp[i][j - 1];
					}
					else if (coins[j - 1] == i) { // haven't used this method before
						dp[i][j] = dp[i][j - 1] + 1;
					}
					else { // cant cover
						dp[i][j] = dp[i][j - 1];
					}
				}
			}
			return dp[amount][n];
		}
	}
}
