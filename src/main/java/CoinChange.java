import java.util.Arrays;
import java.util.Map;

/**
 * 322
 *
 * Compute the fewest number of coins that you need to make up the amount.
 */
public class CoinChange {
    /**
     * Bottom-up solution
     * Time complexity: O(amount*coins.length)
     * Space complexity: O(amount)
     * Recurrence: total[i] = min(total[i], 1 + total[i - coins[j]])
     *
     * @param amount total to return with change
     * @param coins  coin denominations
     * @return last item in the array
     */
    private int SolutionBU(int amount, int[] coins) {
        int[] total = new int[amount + 1];
        Arrays.fill(total, Integer.MAX_VALUE - 1);
        total[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    // if it is possible to give
                    // change with coin of denomination j
                    total[i] = Math.min(total[i], 1 + total[i - coin]);
                }
            }
        }
        return total[amount] > amount ? -1 : total[amount];
    }

    /**
     * Top-down solution
     * Time complexity: O(amount*coins.length)
     * Space complexity: O(amount)
     */
    private int SolutionTD(int amount, int[] coins, Map<Integer, Integer> map) {
        // it takes 0 coins to form a total of 0
        if (amount == 0) return 0;
        // memoization, how many coins it takes to form a total
        if (map.containsKey(amount)) return map.get(amount);
        int min = Integer.MAX_VALUE;
        // iterate through all coins to see which coin will give the best result
        for (int coin : coins) {
            if (coin > amount) continue;
            min = Math.min(min, SolutionTD(amount - coin, coins, map));
        }
        min = min == Integer.MAX_VALUE ? min : min + 1;
        map.put(amount, min);
        return min;
    }
}
