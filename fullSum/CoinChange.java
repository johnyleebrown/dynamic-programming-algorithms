package dp.fullSum;

/**
 * 322
 */
public class CoinChange {
    /**
     * 1. dp[i] - smallest n of c for amount i
     *
     * 2. with what ends with - either we take a coint + 1 or we don't
     */
    class Solution {
        private int[] dp;

        public int coinChange(int[] coins, int amount) {

            dp = new int[amount + 1];
            dfs(amount, coins);
            return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        }

        private int dfs(int s, int[] coins) {
            if (s < 0)
                return -1;
            if (s == 0)
                return 0;
            if (dp[s] != 0)
                return dp[s];

            int ans = Integer.MAX_VALUE;
            for (int c : coins)
                if (dfs(s - c, coins) != -1)
                    ans = Math.min(ans, dfs(s - c, coins) + 1);

            dp[s] = ans == Integer.MAX_VALUE ? -1 : ans;
            return dp[s];
        }
    }
}
