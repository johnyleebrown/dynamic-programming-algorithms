/**
 * 198
 * Given a list of non-negative integers representing the amount of
 * money of each house, determine the maximum amount of money you can rob.
 * Two adjacent houses can't be broken into on the same night
 */
public class HouseRobber {

    // O(n), O(n)
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++)
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        return dp[nums.length];
    }
}
