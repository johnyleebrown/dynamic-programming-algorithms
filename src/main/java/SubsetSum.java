/**
 * Subset Sum
 * Given a set of non-negative integers, and a value sum,
 * determine if there is a subset of the given set with sum equal to given sum.
 */
public class SubsetSum {
    /**
     * Algorithm:
     * 2d array, number*set.length, each cell represents boolean answer
     * if j can be formed with current subset
     * Time complexity: O(set.length*n)
     * Space complexity: O(set.length*n)
     */
    // we can always make 0 with an empty set
    public static boolean solution(int[] set, int n) {
        boolean[][] temp = new boolean[set.length + 1][n + 1];
        for (int i = 0 ; i <= set.length ; i++) temp[i][0] = true;
        for (int i = 1 ; i <= set.length ; i++) {
            for (int j = 1 ; j <= n ; j++) {
                if (j < set[i - 1])
                    temp[i][j] = temp[i - 1][j];
                else
                    temp[i][j] = temp[i - 1][j] || temp[i - 1][j - set[i - 1]];
            }
        }
        return temp[set.length][n];
    }
}
