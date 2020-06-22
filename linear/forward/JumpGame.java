package dp.linear.forward;

/**
 * 55
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
 * Determine if you are able to reach the last index.
 *
 * ======
 *
 * Source: Leetcode
 */
public class JumpGame {
    public static class Solution {
        public boolean canJump(int[] a) {
            int max = 0;
            for (int i = 0; i < a.length; i++) {
                if (i > max) return false;
                max = Math.max(max, i + a[i]);
            }
            return true;
        }
    }

    /**
     * DP solution.
     */
    public static class Solution2 {
        public boolean canJump(int[] a) {
            return dfs(0, new Boolean[a.length], a);
        }

        private boolean dfs(int i, Boolean[] dp, int[] a) {
            if (i >= a.length) {
                return false;
            }
            if (i == a.length - 1) {
                return true;
            }
            if (dp[i] != null) return dp[i];
            boolean ans = false;
            for (int j = i + a[i]; j >= i + 1; j--) {
                ans = ans || dfs(j, dp, a);
            }
            dp[i] = ans;
            return ans;
        }
    }
}