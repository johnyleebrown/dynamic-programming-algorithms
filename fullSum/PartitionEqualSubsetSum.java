package dp.fullSum;

import java.util.Arrays;

/**
 * 416
 */
public class PartitionEqualSubsetSum {
	/**
	 * Either we add i or not.
	 */
	public static class Solution {
		public boolean canPartition(int[] ar) {
			int sum = 0;
			int max = ar[0];
			for (int i : ar) {
				max = Math.max(max, i);
				sum += i;
			}
			if (sum % 2 == 1) return false;
			int target = sum / 2;
			if (max > target) return false;
			Arrays.sort(ar); // we go from right to faster gain sum
			return dfs(ar.length - 1, 0, target, ar);
		}

		private boolean dfs(int i, int sum, int target, int[] ar) {
			if (i < 0 || sum > target) return false;
			if (sum == target) return true;
			return dfs(i - 1, sum + ar[i], target, ar)
					|| dfs(i - 1, sum, target, ar);
		}
	}
}
