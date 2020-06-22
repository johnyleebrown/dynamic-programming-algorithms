package dp.subsequences.lcs;

/**
 * 1458
 *
 * ======
 *
 * Task.
 *
 * Given two arrays nums1 and nums2.
 *
 * Return the maximum dot product between non-empty subsequences of nums1 and
 * nums2 with the same length.
 *
 * A subsequence of a array is a new array which is formed from the original
 * array by deleting some (can be none) of the characters without disturbing the
 * relative positions of the remaining characters. (ie, [2,3,5] is a subsequence
 * of [1,2,3,4,5] while [1,5,3] is not).
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaxDotProductOfTwoSubsequences {
    /**
     * LCS problem, 3 variants at each step.
     */
    public static class Solution {
        public int maxDotProduct(int[] ar1, int[] ar2) {
            int n1 = ar1.length, n2 = ar2.length;
            int ans = Integer.MIN_VALUE;

            int[][] dp = new int[n1 + 1][n2 + 1];
            dp[0][0] = ar1[0] * ar2[0];
            for (int i = 1; i < n1; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], ar1[i] * ar2[0]);
                ans = Math.max(ans, dp[i][0]);
            }
            for (int j = 1; j < n2; j++) {
                dp[0][j] = Math.max(dp[0][j - 1], ar1[0] * ar2[j]);
                ans = Math.max(ans, dp[0][j]);
            }

            for (int i = 1; i < n1; i++) {
                for (int j = 1; j < n2; j++) {
                    // either we take (i,j) or not
                    dp[i][j] = Math.max(ar1[i] * ar2[j], dp[i - 1][j - 1] + ar1[i] * ar2[j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                    ans = Math.max(ans, dp[i][j]);
                }
            }

            return ans == Integer.MIN_VALUE ? -1 : ans;
        }
    }
}