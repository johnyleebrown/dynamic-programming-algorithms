package dp.trees;

import util.utils.reader.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.utils.Out.printAr;

/**
 * 627div3F
 *
 * ======
 *
 * Task.
 *
 * You are given a tree consisting of 𝑛 vertices. A tree is a connected
 * undirected graph with 𝑛−1 edges. Each vertex 𝑣 of this tree has a color
 * assigned to it (𝑎𝑣=1 if the vertex 𝑣 is white and 0 if the vertex 𝑣 is
 * black).
 *
 * You have to solve the following problem for each vertex 𝑣 : what is the
 * maximum difference between the number of white and the number of black
 * vertices you can obtain if you choose some subtree of the given tree that
 * contains the vertex 𝑣? The subtree of the tree is the connected subgraph of
 * the given tree. More formally, if you choose the subtree that contains
 * 𝑐𝑛𝑡𝑤 white vertices and 𝑐𝑛𝑡𝑏 black vertices, you have to maximize
 * 𝑐𝑛𝑡𝑤−𝑐𝑛𝑡𝑏.
 *
 * ======
 *
 * Source: Codeforces
 */
public class MaximumWhiteSubtree {
    /**
     * TODO
     */
    public static class Solution {
        private static int[][] dp;

        private static void s(InputReader in, PrintWriter o) {
            int n = in.nextInt();

            dp = new int[n + 1][n + 1];
            int[] clr = new int[n + 1];
            List<Integer>[] g = new List[n + 1];

            Arrays.fill(dp[0], Integer.MIN_VALUE);
            for (int i = 1; i <= n; i++) {
                clr[i] = in.nextInt();
                Arrays.fill(dp[i], Integer.MIN_VALUE);
            }

            // init graph
            for (int i = 0; i <= n; i++)
                g[i] = new ArrayList<>();

            // fill graph
            for (int i = 0; i < n - 1; i++) {
                int w = in.nextInt();
                int v = in.nextInt();
                g[w].add(v);
                g[v].add(w);
            }

            int[] res = new int[n];
            for (int i = 1; i <= n; i++)
                res[i - 1] = dfs(0, i, clr, g);

            printAr(o, res);
        }

        private static int dfs(int prev, int cur, int[] clr, List<Integer>[] g) {
            if (dp[prev][cur] != Integer.MIN_VALUE)
                return dp[prev][cur];//if calculated before
            int curval = clr[cur] == 1 ? 1 : -1;//real node value
            int goodsum = 0;//count only >0
            for (int w : g[cur]) {
                if (w == prev)
                    continue;
                int x = dfs(cur, w, clr, g);
                if (x > 0)
                    goodsum += x;
            }
            dp[prev][cur] = Math.max(dp[prev][cur], goodsum + curval);//this case includes self
            return dp[prev][cur];
        }
    }
}
