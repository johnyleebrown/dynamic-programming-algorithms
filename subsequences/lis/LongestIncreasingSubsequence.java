package dp.subsequences.lis;

import java.util.Arrays;

/**
 * 300
 */
public class LongestIncreasingSubsequence {
	/**
	 * O(n^2).
	 *
	 * At each i go back to find max increasing sequence.
	 */
	public static class Solution2 {
		public int lengthOfLIS(int[] a) {
			int n = a.length;
			if (n == 0) {
				return 0;
			}
			int ans = 1;
			int[] dp = new int[n + 1];
			Arrays.fill(dp, 1); // length of lis of len 1 is 1
			dp[0] = 0;
			for (int i = 2; i <= n; i++) {
				for (int j = i - 1; j >= 1; j--) {
					if (a[i - 1] > a[j - 1]) {
						dp[i] = Math.max(dp[j] + 1, dp[i]);
						ans = Math.max(ans, dp[i]);
					}
				}
			}
			return ans;
		}
	}
}