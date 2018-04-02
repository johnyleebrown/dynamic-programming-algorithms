/**
 * 188
 * Say you have an array for which the ith element is the price
 * of a given stock on day i.
 * Design an algorithm to find the maximum profit.
 * You may complete at most k transactions.
 * you must sell the stock before you buy again
 */
public class BestTimetoBuyandSellStockIV {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0 || k == 0) return 0;
        return solution(k, prices);
    }

    // on LC = MLE
    public static int solution(int k, int[] prices) {
        // transactions x days
        // temp [i][j] = max profit from at most i transactions till j-th day
        int[][] temp = new int[k + 1][prices.length];
        // can't make any profit having only one day
        // no profit with zero transactions
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= prices.length - 1; j++) {
                int max = 0;
                for (int l = 0; l < j; l++) {
                    // preforming a transaction with max profit
                    // comparing the diff between current and all prev days
                    // left with i - 1 transactions which we should finish till the l-th day
                    max = Math.max(max, prices[j] - prices[l] + temp[i - 1][l]);
                }
                // we don't do transactions in the current day
                // so we take the max profit from the prev day
                temp[i][j] = Math.max(max, temp[i][j - 1]);
            }

        }
        return temp[k][prices.length - 1];
    }

    // on LC = MLE
    // in the formula max 0..j-1 (prices[j] - prices[l] + temp[i - 1][l])
    // prices[j] stays the same and the ( - prices[l] + temp[i - 1][l] ) part changes dep on l
    // we can compute max of this value on every iteration instead
    public static int solutionOptimized(int k, int[] prices) {
        int[][] temp = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++) {
            // on the first iteration temp[i - 1][l] is zero
            int val = -prices[0];
            for (int j = 1; j <= prices.length - 1; j++) {
                temp[i][j] = Math.max(temp[i][j - 1], prices[j] + val);
                val = Math.max(val, temp[i - 1][j] - prices[j]);
            }
        }
        return temp[k][prices.length - 1];
    }

    // on LC = TLE
    // more optimized temp[k+1][prices.length] -> temp[2][prices.length]
    public static int solutionOptimized2(int k, int[] prices) {
        int[][] temp = new int[2][prices.length];
        int x = 1;
        for (int i = 1; i <= k; i++) {
            int val = -prices[0];
            for (int j = 1; j <= prices.length - 1; j++) {
                temp[x][j] = Math.max(temp[x][j - 1], prices[j] + val);
                val = Math.max(val, temp[1 - x][j] - prices[j]);
            }
            x = 1 - x;
        }
        return k % 2 == 0 ? temp[0][prices.length - 1] : temp[1][prices.length - 1];
    }

    // time optimized in special cases
    public static int solutionOptimized3(int k, int[] prices) {
        if (k >= prices.length/2) {
            int sum = 0;
            for (int i = 1; i <= prices.length - 1; i++)
                if (prices[i] > prices[i - 1])
                    sum += prices[i] - prices[i - 1];
            return sum;
        } else return solutionOptimized2(k, prices);
    }
}
