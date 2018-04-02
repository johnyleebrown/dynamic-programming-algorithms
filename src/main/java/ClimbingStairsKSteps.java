/**
 * Count the number of ways to climb the n-th step
 * if it is possible to climb from 1 to k steps at a time
 */
public class ClimbingStairsKSteps {
    /**
     * Time complexity: O(n*k)
     * Space complexity: O(n)
     */
    public static int solution(int n, int k) {
        int[] a = new int[n + 1];
        a[0] = 1;
        for (int i = 1 ; i <= n ; i++) {
            // so we don't have to count negative climbs
            // as they are equal to zero
            int max = Math.min(i, k);
            for (int j = 1 ; j <= max ; j++)
                a[i] += a[i - j];
        }
        return a[n];
    }

    // O(n) time
    public static int solution2(int n, int k) {
        int[] a = new int[n + 1];
        a[0] = 1;
        a[1] = 1;
        int sum = a[0] + a[1];
        for (int i = 2 ; i <= n ; i++) {
            int max = Math.min(i, k);
            if (i - max - 1 >= 0)
                sum -= a[i - max - 1];
            a[i] = sum;
            sum += a[i];
        }
        return a[n];
    }

}
