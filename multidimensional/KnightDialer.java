package dp.multidimensional;

import util.tester.Tester;

/**
 * 935
 *
 * ======
 *
 * Task.
 *
 * This time, we place our chess knight on any numbered key of a phone pad
 * (indicated above), and the knight makes N-1 hops.  Each hop must be from one
 * key to another numbered key.
 *
 * Each time it lands on a key (including the initial placement of the knight),
 * it presses the number of that key, pressing N digits total.
 *
 * How many distinct numbers can you dial in this manner?
 *
 * Since the answer may be large, output the answer modulo 10^9 + 7.
 *
 * ======
 *
 * Source: Leetcode
 */
public class KnightDialer
{
	/**
	 * Linear dp. If we have calculated an item before we use it. The trick is
	 * to use a dp array for each kind of n.
	 */
	public static class Solution
	{
		int[][] dirs = new int[][]{{1, 2}, {2, 1}, {-1, 2}, {1, -2}, {-1, -2}, {2, -1}, {-2, 1}, {-2, -1}};
		int[][][] dp;
		int n = 4;
		int m = 3;
		int mod = (int) (1e9) + 7;

		public int knightDialer(int target)
		{
			// number of variants to get from i,j at some level of recursion
			dp = new int[target + 1][n][m];
			int res = 0;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					res = (res + dfs(i, j, target)) % mod;
			return res;
		}

		private int dfs(int i, int j, int target)
		{
			if (i < 0 || j < 0 || i >= n || j >= m || (i == 3 && j == 0) || (i == 3 && j == 2))
				return 0;
			if (target == 1)
				return 1;
			if (dp[target][i][j] != 0)
				return dp[target][i][j];
			for (int[] d : dirs)
				dp[target][i][j] = (dp[target][i][j] + dfs(i + d[0], j + d[1], target - 1)) % mod;
			return dp[target][i][j];
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(1).expect(10)
				.add(2).expect(20)
				.add(3).expect(46)
				.add(17).expect(4942848)
				.add(161).expect(533302150)
				.run();
	}
}
