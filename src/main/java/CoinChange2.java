/**
 * 518
 *
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 */
public class CoinChange2 {
    /**
     * Similarly to knapsack problem we use 2d array to store number of
     * combinations for each coin for each change amount
     *
     * Time complexity: O(coins*amount)
     * Space complexity: O(coins*amount)
     */
    private static int solution(int[] coins, int amount) {
        int[][] k = new int[coins.length + 1][amount + 1];
        for (int i = 0 ; i <= coins.length ; i++) k[i][0] = 1;
        for (int i = 1; i <= coins.length; i++)
            for (int j = 1; j <= amount; j++)
                if (coins[i - 1] > j) k[i][j] = k[i - 1][j];
                else k[i][j] = k[i - 1][j] + k[i][j - coins[i - 1]];

        return k[coins.length][amount];
    }

    /**
     * Optimized 2DA -> 1DA
     *
     * Time O(coins*amount)
     * Space O(amount)
     */
    private static int solutionOptimized(int[] coins, int amount) {
        int[] temp = new int[amount + 1];
        temp[0] = 1;
        for (int coin : coins)
            for (int j = 1; j <= amount; j++)
                if (coin <= j)
                    temp[j] += temp[j - coin];

        return temp[amount];
    }
}
