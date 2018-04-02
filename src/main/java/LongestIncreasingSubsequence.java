import java.util.Arrays;

/**
 * 300
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example, Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 */
public class LongestIncreasingSubsequence {
    /**
     * For each of the elements count the lis
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    public static int solution(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1); // the lis of arrays of length 1 is 1
        int maxInd = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);

                    // find max
                    if (max < memo[i]) {
                        max = memo[i];
                        maxInd = i;
                    }
                }
            }
        }

        return memo[maxInd];
    }

    // binary search
    public int solution2(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);

            if (i < 0)
                i = -(i + 1);

            dp[i] = num;

            if (i == len)
                len++;
        }

        return len;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,4,-1,0,6,2,3}));
        System.out.println(solution(new int[]{1,3,6,7,9,4,10,5,6}));
    }
}
