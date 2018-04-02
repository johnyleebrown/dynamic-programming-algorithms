/**
 * 746
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * Once you pay the cost, you can either climb 1 or 2 steps.
 * You need to find minimum cost to reach the top of the floor,
 * and you can either start from the step with index 0, or the step with index 1.
 */
public class MinCostClimbingStairs {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(int[] values) {
        int[] a = new int[values.length + 1];
        // the i-th step has cost cost[i]
        a[0] = values[0];
        a[1] = values[1];
        for (int i = 2 ; i <= values.length ; i++) {
            // the ending's cost is zero
            int val = i != values.length ? values[i] : 0;
            a[i] = Math.min(a[i - 1], a[i - 2]) + val;
        }
        return a[values.length];
    }
}
