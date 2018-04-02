/**
 * 276
 * fence with n posts, each post can be painted with one of the k colors
 * no more than two adjacent posts have same color
 * return total num of ways
 */
public class PaintFence {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(int n, int k) {
        int[] temp = new int[n + 1];
        temp[0] = k/(k-1);
        temp[1] = k;
        for (int i = 2; i <= n; i++)
            // either i-th post and i-1 post are the same color
            // or they are diff colors
            temp[i] = temp[i-1] * (k-1) + temp[i-2] * (k-1);
        return temp[n];
    }

}
