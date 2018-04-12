import java.util.HashMap;

/**
 * // 325
 * <p>
 * // Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
 * // If there isn’t one, return 0 instead.
 * <p>
 * // Example 1:
 * // Given nums = [1, -1, 5, -2, 3], k = 3,
 * // return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * <p>
 * // Example 2:
 * // Given nums = [-2, -1, 2, 1], k = 1,
 * // return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * <p>
 * // Follow Up:
 * // Can you do it in O(n) time?
 */
public class MaximumSizeSubarraySumEqualsK {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, res = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) // sum - k = the entry where the list should start
                res = Math.max(res, i - map.get(sum - k));
            else
                map.put(sum, i);
        }

        return res;
    }
}
