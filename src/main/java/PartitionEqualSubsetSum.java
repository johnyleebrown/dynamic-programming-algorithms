/**
 * 416
 * Given a non-empty array containing only positive integers,
 * find if the array can be partitioned into two subsets such
 * that the sum of elements in both subsets is equal.
 */
public class PartitionEqualSubsetSum {
    /**
     * Time complexity: O(set.length*sum/2)
     * Space complexity: O(set.length*sum/2)
     */
    public static boolean solution(int[] set) {
        int sum = 0;
        for (int num : set) sum += num;
        if (sum % 2 != 0) return false;
        sum /= 2;

        boolean[][] temp = new boolean[set.length + 1][sum + 1];
        for (int i = 0 ; i <= set.length ; i++) temp[i][0] = true;

        for (int i = 1 ; i <= set.length ; i++) {
            for (int j = 1 ; j <= sum ; j++) {
                if (set[i - 1] > j)
                    temp[i][j] = temp[i - 1][j];
                else
                    temp[i][j] = temp[i - 1][j] || temp[i - 1][j - set[i - 1]];
            }
        }

        return temp[set.length][sum];
    }
    // can be optimized 2d -> 1d
}
