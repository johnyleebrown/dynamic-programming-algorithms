/**
 * 801
 *
 * We have two integer sequences A and B of the same non-zero length.
 * We are allowed to swap elements A[i] and B[i].  Note that both
 * elements are in the same index position in their respective sequences.
 * At the end of some number of swaps, A and B are both strictly increasing.
 * (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.
 * It is guaranteed that the given input always makes it possible.
 *
 * [0,6,3,12] [1,1,7,5]
 * [0,4,4,5,9] [0,1,6,8,10]
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int minSwap(int[] A, int[] B) {
        int[] memo1 = new int[A.length];
        int[] memo2 = new int[A.length];
        memo2[0] = 1;

        for (int i = 1; i < A.length; i++) {
            memo1[i] = Integer.MAX_VALUE;
            memo2[i] = Integer.MAX_VALUE;

            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                memo1[i] = Math.min(memo1[i - 1], memo1[i]);
                memo2[i] = Math.min(memo2[i - 1] + 1, memo2[i]);
            }

            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                memo1[i] = Math.min(memo2[i - 1], memo1[i]);
                memo2[i] = Math.min(memo1[i - 1] + 1, memo2[i]);
            }
        }

        return Math.min(memo1[A.length - 1], memo2[A.length - 1]);
    }

    public static void main(String[] args) {
        System.out.println(minSwap(new int[]{0,4,4,5,9},new int[]{0,1,6,8,10}));
    }
}
