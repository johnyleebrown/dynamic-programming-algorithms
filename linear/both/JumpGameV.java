package dp.linear.both;

/**
 * 1340
 *
 * ======
 *
 * Task.
 *
 * Given an array of integers arr and an integer d. In one step you can jump
 * from index i to index:
 *
 * i + x where: i + x < arr.length and 0 < x <= d. i - x where: i - x >= 0 and 0
 * < x <= d.
 *
 * In addition, you can only jump from index i to index j if arr[i] > arr[j] and
 * arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) <
 * k < max(i, j)).
 *
 * You can choose any index of the array and start jumping. Return the maximum
 * number of indices you can visit.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * ======
 *
 * Source: Leetcode
 */
public class JumpGameV {
	/**
	 * Straightforward. Similar to frog jump, either we jump to position (+1)
	 * and finish or we jump to position (+1) and jump somewhere else as well
	 * (+dfs).
	 */
	class Solution {
		private Integer[] dp;

		public int maxJumps(int[] arr, int d) {
			dp = new Integer[arr.length];
			int res = Integer.MIN_VALUE;
			for (int i = 0; i < arr.length; i++) {
				res = Math.max(res, dfs(i, arr, d));
			}
			return res;
		}

		private int dfs(int cur, int[] arr, int d) {
			if (dp[cur] != null) {
				return dp[cur];
			}

			// we either jump here (+1) and finish or we jump here (+1) and smw else (+dfs)
			int res = 1;

			// from cur-1 to left border
			for (int i = cur - 1; i >= 0 && i >= cur - d && arr[i] < arr[cur]; i--) {
				res = Math.max(res, dfs(i, arr, d) + 1);
			}

			// from cur+1 to right border
			for (int i = cur + 1; i < arr.length && i <= cur + d && arr[i] < arr[cur]; i++) {
				res = Math.max(res, dfs(i, arr, d) + 1);
			}

			dp[cur] = res;
			return res;
		}
	}
}