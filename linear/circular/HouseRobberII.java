package dp.linear.circular;

import util.tester.Tester;

import java.util.Arrays;

/**
 * 213
 *
 * ======
 *
 * Task.
 *
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed. All houses at this place are
 * arranged in a circle. That means the first house is the neighbor of the last
 * one. Meanwhile, adjacent houses have security system connected and it will
 * automatically contact the police if two adjacent houses were broken into on
 * the same night.
 *
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 *
 * ======
 *
 * Similar: HRI,Pizza Slices
 *
 * ======
 *
 * Source: Leetcode
 */
public class HouseRobberII {
	/**
	 * dp[i] - am of money if ending at ith house. we can pick pick this one or
	 * not - dp[i]=max(dp[i-2]+cur,dp[i-1]) note that if we take 0, we cant take
	 * last and vice versa.
	 */
	public static class Solution {
		public int rob(int[] a) {
			int n = a.length;
			if (n == 0) {
				return 0;
			}
			if (n == 1) {
				return a[0];
			}

			int[] dp1 = new int[n + 1];
			int[] dp2 = new int[n + 1];
			Arrays.fill(dp1, -1);
			dp1[0] = 0;
			dp1[1] = 0;
			Arrays.fill(dp2, -1);
			dp2[0] = 0;

			return Math.max(dfs(n, dp1, a), dfs(n - 1, dp2, a));
		}

		private int dfs(int i, int[] dp, int[] a) {
			if (i < 0) {
				return 0;
			}
			if (dp[i] != -1) {
				return dp[i];
			}
			dp[i] = Math.max(dfs(i - 1, dp, a), dfs(i - 2, dp, a) + a[i - 1]);
			return dp[i];
		}

		public Solution() {
		}
	}

	public static void main(String[] args) {
		new Tester(new Solution())
				.add(new int[]{2, 3, 2}).expect(3)
				.add(new int[]{1, 2, 3, 1}).expect(4)
				.add(new int[]{1, 23, 12, 13, 31, 7, 8, 5, 1, 3, 6, 9, 7, 4, 2, 21}).expect(99)
				.run();
	}
}
