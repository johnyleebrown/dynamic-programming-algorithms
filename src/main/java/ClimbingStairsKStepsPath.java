/**
 * While climbing to the n-th step
 * with 1 to k steps at a time
 * compute the path
 */
public class ClimbingStairsKStepsPath {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n^2)
     */
    public static int[] solution(int k, int[] values, int n) {
        int[] a = new int[n + 1];
        a[0] = 1;
        int[] from = new int[n];
        for (int i = 1 ; i <= n ; i++) {
            int max = Math.min(i, k);
            // init
            a[i] = a[i - 1] + values[i - 1];
            from[i - 1] = i - 1;
            for (int j = 1 ; j <= max ; j++) {
                if (a[i] > a[i - j] + values[i - 1]) {
                    a[i] = a[i - j] + values[i - 1];
                    from[i - 1] = i - j;
                }
            }
        }
        return from;
    }

}
