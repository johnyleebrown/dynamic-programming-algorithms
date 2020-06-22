package dp.acyclicGraph;

import util.tester.Tester;
import util.tester.TesterPreprocessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 787
 */
public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        new Tester(new Solution())
                .add(3, TesterPreprocessor.ararFromString("[[0,1,100],[1,2,100],[0,2,500]]"), 0, 2, 1).expect(200)
                .add(4, TesterPreprocessor.ararFromString("[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]"), 0, 3, 1).expect(6)
                .add(5, TesterPreprocessor.ararFromString("[[0,1,5],[1,2,5],[0,3,2],[3,1,2],[1,4,1],[4,2,1]]"), 0, 2, 2).expect(7)
                .run();
    }

    @SuppressWarnings("unchecked")
    public static class Solution {
        private List<Integer>[] indegree;
        private int[][] cost;
        private int[][] ans;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            ans = new int[n + 1][k + 2];
            indegree = new List[n];
            cost = new int[n][n];

            // base
            for (int i = 0; i <= n; i++)
                if (i != src)
                    Arrays.fill(ans[i], Integer.MAX_VALUE);

            // preprocess
            for (int i = 0; i < n; i++) {
                indegree[i] = new ArrayList<>();
            }
            for (int[] flight : flights) {
                indegree[flight[1]].add(flight[0]);
                cost[flight[0]][flight[1]] = flight[2];
            }

            int res = dfs(dst, k + 1, src);
            // edge case
            return res == Integer.MAX_VALUE ? -1 : res;
        }

        private int dfs(int dst, int k, int src) {
            // memo
            if (ans[dst][k] != Integer.MAX_VALUE)
                return ans[dst][k];

            for (int w : indegree[dst]) {
                // if previous node is source, we go only there
                // and ignore other indegree nodes
                if (k - 1 == 0 && w != src)
                    continue;

                int minCostToW = dfs(w, k - 1, src);
                // edge case
                if (minCostToW != Integer.MAX_VALUE)
                    ans[dst][k] = Math.min(ans[dst][k], minCostToW + cost[w][dst]);
            }

            return ans[dst][k];
        }
    }
}