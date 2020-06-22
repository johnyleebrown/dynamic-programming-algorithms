package dp.fullSum;

/**
 * 813
 */
public class LargestSumOfAverages {
    /**
     * You are building up a list of averages for each i,k index
     * Similar to the buying and selling of stock question, at each increment of
     * k you are leverage off the previous k best average and trying to
     * determine how the best average based on the addition of the new index.
     * With the new index, there are different possibilities of how the average
     * could be. You need to find the max of those and that will be equal to
     * your solution for i,k
     *
     * dp(i, k) = max(average(i, N), max_{j > i}(average(i, j) + dp(j, k-1))).
     */
    public static class Solution {
        public double largestSumOfAverages(int[] A, int K) {
            int N = A.length;
            double[][] dp = new double[N + 1][N + 1];
            double cur = 0;

            for (int i = 0; i < N; ++i) {
                cur += A[i];
                dp[i + 1][1] = cur / (i + 1);
            }

            return dfs(N, K, A, dp);
        }

        public double dfs(int N, int K, int[] A, double[][] dp) {
            if (dp[N][K] > 0) {
                return dp[N][K];
            }
            double cur = 0;

            for (int i = N - 1; i > 0; i--) {
                cur += A[i];
                dp[N][K] = Math.max(dp[N][K], dfs(i, K - 1, A, dp) + cur / (N - i));
            }

            return dp[N][K];
        }
    }
}
