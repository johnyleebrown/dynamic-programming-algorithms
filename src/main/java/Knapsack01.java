/**
 * 0-1 Knapsack problem
 *
 * Given weights and values of n items,
 * put these items in a knapsack of capacity W
 * to get the maximum total value in the knapsack.
 * You cannot break an item, either pick the complete item,
 * or don’t pick it (0-1 property).
 */
public class Knapsack01 {
    /**
     * @param values array of values of items in the knapsack
     * @param weights array of weights of items in the knapsack, indexes correspond to indexes in {@code val}
     * @param capacity knapsack capacity
     * @return maximum value of items
     */
    public static int Knapsack01(int[] values, int[] weights, int capacity) {
        /*
          Algorithm:
          Building a 2d array ixj
          It will hold all of the best solutions to the sub problems
          by finding the maximum profit of each item at each weight
          a[i][j] = Math.max(a[i-1][j], val[i] + a[i-1][j-w[i]]
          The value in the bottom right of the array is the maximum profit
         */
        int k[][] = new int[values.length + 1][capacity + 1];
        for (int i = 0; i <= values.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0)
                    k[i][j] = 0;
                else if (j < weights[i - 1])
                    // if we can't put an item of weight[i - 1] into knapsack of cap w
                    k[i][j] = k[i - 1][j];
                else
                    // either we don't pick an item: take the prev max
                    // or we do: take max cost for j - weights[i - 1] weight and add item's value
                    k[i][j] = Math.max(k[i - 1][j], k[i - 1][j - weights[i - 1]] + values[i - 1]);
            }
        }
        return k[values.length][capacity];
    }
}
