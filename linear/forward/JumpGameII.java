package dp.linear.forward;

import java.util.Arrays;

/**
 * 45
 *
 * ======
 *
 * Task.
 *
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 *
 * Each element in the array represents your maximum jump length at that
 * position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * ======
 *
 * Source: Leetcode
 */
public class JumpGameII
{
	/**
	 * DP forward precalc.
	 */
	public static class Solution
	{
		public int jump(int[] a)
		{
			int n = a.length;
			int[] dp = new int[n];
			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[0] = 0;
			for (int i = 0; i < n; i++)
				for (int j = i + 1; j <= Math.min(i + a[i], n - 1); j++)
					dp[j] = Math.min(dp[j], dp[i] + 1);
			return dp[n - 1];
		}
	}

	/**
	 * BFS.
	 */
	public static class Solution2
	{
		public int solve()
		{
			return -1;
		}
	}
}
