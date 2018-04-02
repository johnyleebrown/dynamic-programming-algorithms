/**
 * 70
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {

    // Brute Force
    // O(n^2) Size of recursion tree will be 2^n
    // O(n)
    public class Solution1 {
        public int climbStairs(int n) {
            return climbRecursive(0, n);
        }

        public int climbRecursive(int i, int n) {
            if (i > n) return 0;
            if (i == n) return 1;
            return climbRecursive(i + 1, n) + climbRecursive(i + 2, n);
        }
    }

    // Recursion with memorization
    // O(n) ; O(n)
    class Solution2{
        public int climbStairs(int n) {
            int memo[] = new int[n + 1];
            return climbRecursive(0, n, memo);
        }

        public int climbRecursive(int i, int n, int memo[]) {
            if (i > n) return 0;
            if (i == n) return 1;
            if (memo[i] > 0) return memo[i];
            memo[i] = climbRecursive(i + 1, n, memo)
                    + climbRecursive(i + 2, n, memo);
            return memo[i];
        }
    }

    // Iterative
    // O(n) ; O(n)
    public class Solution3 {
        public int climbStairs(int n) {
            int[] a = new int[n + 1];
            a[0] = 1;
            a[1] = 1;
            for (int i = 2; i <= n; i++)
                a[i] = a[i - 1] + a[i - 2];
            return a[n];
        }
    }

    // Iterative optimized
    // O(n) ; O(1)
    static class Solution4 {
        public static int climbStairs(int n) {
            if (n == 1) return 1;
            int first = 1;
            int second = 2;
            for (int i = 3; i <= n; i++) {
                int third = first + second;
                first = second;
                second = third;
            }
            return second;
        }
    }

}
