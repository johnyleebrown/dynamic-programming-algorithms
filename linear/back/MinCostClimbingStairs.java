package dp.linear.back;

/**
 * 746
 */
public class MinCostClimbingStairs {
	public static int solution(int[] values) {
		int[] a = new int[values.length + 1];
		// the i-th step has cost cost[i]
		a[0] = values[0];
		a[1] = values[1];
		for (int i = 2; i <= values.length; i++) {
			// the ending's cost is zero
			int val = i != values.length ? values[i] : 0;
			a[i] = Math.min(a[i - 1], a[i - 2]) + val;
		}
		return a[values.length];
	}
}
