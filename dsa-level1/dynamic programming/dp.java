
/**
 * dp
 */
import java.util.*;

public class dp {

    public static int fib_rec(int n, int[] dp) {
        if (n == 0 || n == 1) {
            return dp[n] = n;
        }

        // if problem is already solved,then return the answer
        if (dp[n] != 0) {
            return dp[n];
        }
        int fibnm1 = fib_rec(n - 1, dp);
        int fibnm2 = fib_rec(n - 2, dp);
        int fibn = fibnm1 + fibnm2;

        // 2.if not solved then solve the problem and store it in the dp array
        return dp[n] = fibn;
    }

    public static int fib_tab(int n, int dp[]) {
        // 1.figure out repetition->repitition on n
        // 2.make a storage of size n+1
        // 3.Assign meaning to the cell->dp[i]=fib[i]=fib[i-1]+fib[i-2];
        // 4.smallest problem is at n=0;
        // 5.prereq->base case

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }

    public static int climbStair_tab(int n, int[] dp) {
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= 3; j++) {
                if (i - j >= 0) {
                    count += dp[i - j];
                }
            }
            dp[i] = count;
        }
        return dp[n];

    }

    public static int climbStair_tab1(int n, int[] dp) {
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int count = 0;
            if (i >= 3) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            } else if (i >= 2) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n];

    }

    public static int climbStair_memo(int n, int[] dp) {
        if (n == 0) {
            return dp[0] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        int count = 0;
        for (int j = 1; j <= 3; j++) {
            if (n - j >= 0) {
                count += climbstair_rec(n - j);
            }
        }
        return dp[n] = count;

    }

    public static int climbstair_rec(int n) {
        if (n == 0) {
            return 1;
        }
        int count = 0;
        for (int j = 1; j <= 3; j++) {
            if (n - j >= 0) {
                count += climbstair_rec(n - j);
            }
        }
        return count;
    }

    public static void climbStair() {
        int n = 4;
        int res = climbstair_rec(n);
        System.out.println(res);
    }

    // i>current stair;
    // n->total stair
    // jumps[]->jumps allowed at
    public static int climbstair_varjumps_rec(int i, int n, int[] jumps) {
        if (i == n) {
            return 1;
        }

        int count = 0;
        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            count += climbstair_varjumps_rec(i + jump, n, jumps);
        }
        return count;
    }

    public static int climbstair_varjumps_memo(int i, int n, int[] jumps, int dp[]) {
        if (i == n) {
            return dp[i] = 1;
        }

        if (dp[i] != 0) {
            return dp[i];
        }

        int count = 0;
        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            count += climbstair_varjumps_memo(i + jump, n, jumps, dp);
        }

        return dp[i] = count;
    }

    public static int climbStair_VarJumps_tab(int i, int n, int[] dp, int[] jumps) {
        for (i = n; i >= 0; i--) {
            if (i == n) {
                dp[i] = 1;
                continue;
            }

            int count = 0;
            for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
                count += dp[i + jump]; // climbStair_VarJumps_memo(i + jump, n, jumps, dp);
            }
            dp[i] = count;
        }
        return dp[0];
    }

    public static int climbStair_minjumps_rec(int i, int n, int jumps[]) {
        if (i == n) {
            return 0;
        }

        int minJumps = (int) 1e9;

        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            minJumps = Math.min(minJumps, climbStair_minjumps_rec(i + jump, n, jumps));
        }
        return minJumps == 1e9 ? minJumps : minJumps + 1;
    }

    public static int climbStair_minjumps_rec_memo(int i, int n, int jumps[], int dp[]) {
        if (i == n) {
            return dp[i] = 0;
        }

        if (dp[i] != 0)
            return dp[i];

        int minJumps = (int) 1e9;

        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            minJumps = Math.min(minJumps, climbStair_minjumps_rec_memo(i + jump, n, jumps, dp));
        }
        return minJumps == 1e9 ? (dp[i] = minJumps) : (dp[i] = minJumps + 1);
    }

    public static int climbStair_minjumps_rec_tab(int i, int n, int jumps[], int dp[]) {
        for (i = n; i >= 0; i--) {
            if (i == n) {
                dp[i] = 0;
                continue;
            }

            int minJumps = (int) 1e9;

            for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
                minJumps = Math.min(minJumps, dp[i + jump]);
            }
            if (minJumps == 1e9) {
                dp[i] = (int) 1e9;
            } else {
                dp[i] = minJumps + 1;
            }
        }
        return dp[0];
    }

    // ~~~~~~~~~~~~~~~~~~~Min cost Path~~~~~~~~~~~~~~
    public static int mincostpathrec(int[][] maze, int x, int y) {
        if (x == maze.length - 1 && y == maze[0].length - 1) {
            return maze[x][y];
        }
        int minCost = (int) 1e9;
        // right call
        if (y + 1 < maze[0].length) {
            minCost = Math.min(minCost, mincostpathrec(maze, x, y + 1));
        }

        // down call
        if (x + 1 < maze.length) {
            minCost = Math.min(minCost, mincostpathrec(maze, x + 1, y));
        }

        return minCost + maze[x][y];
    }

    public static int mincostpathmemo(int[][] maze, int x, int y, int dp[][]) {
        if (x == maze.length - 1 && y == maze[0].length - 1) {
            return dp[x][y] = maze[x][y];
        }

        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        int minCost = (int) 1e9;
        // right call
        if (y + 1 < maze[0].length) {
            minCost = Math.min(minCost, mincostpathmemo(maze, x, y + 1, dp));
        }

        // down call
        if (x + 1 < maze.length) {
            minCost = Math.min(minCost, mincostpathmemo(maze, x + 1, y, dp));
        }

        return dp[x][y] = minCost + maze[x][y];
    }

    public static int mincostpathtab(int[][] maze, int x, int y, int dp[][]) {
        for (x = maze.length - 1; x >= 0; x--) {
            for (y = maze[0].length - 1; y >= 0; y--) {
                if (x == maze.length - 1 && y == maze[0].length - 1) {
                    dp[x][y] = maze[x][y];
                    continue;
                }

                int minCost = (int) 1e9;
                // right call
                if (y + 1 < maze[0].length) {
                    minCost = Math.min(minCost, dp[x][y + 1]);
                }

                // down call
                if (x + 1 < maze.length) {
                    minCost = Math.min(minCost, dp[x + 1][y]);
                }

                dp[x][y] = minCost + maze[x][y];
            }
        }
        return dp[0][0];
    }

    public static void minCostPath(int[][] dp, int x, int y, String psf) {
        if (x == dp.length - 1 && y == dp[0].length - 1) {
            System.out.println(psf);
        } else if (x == dp.length - 1)
            minCostPath(dp, x, y + 1, psf + "R");
        else if (y == dp[0].length - 1)
            minCostPath(dp, x + 1, y, psf + "D");
        else {
            if (dp[x][y + 1] == dp[x + 1][y]) {
                minCostPath(dp, x + 1, y, psf + "D");
                minCostPath(dp, x, y + 1, psf + "R");
            } else if (dp[x][y + 1] < dp[x + 1][y]) {
                minCostPath(dp, x, y + 1, psf + "R");
            } else {
                minCostPath(dp, x + 1, y, psf + "D");
            }
        }

    }

    public static void mazepath() {
        int[][] maze = { { 0, 1, 4, 2, 8, 2 }, { 4, 3, 6, 5, 0, 4 }, { 1, 2, 4, 1, 4, 6 }, { 2, 0, 7, 3, 2, 2 },
                { 3, 1, 5, 9, 2, 4 }, { 2, 7, 0, 8, 5, 1 } };
        int dp[][] = new int[maze.length][maze[0].length];
        mincostpathtab(maze, 0, 0, dp);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        minCostPath(dp, 0, 0, "");
    }

    public static int goldmineHelper_rec(int mine[][], int x, int y) {
        if (y == mine[0].length - 1) {
            return mine[x][y];
        }
        int cost = 0;
        // top-right
        if (x - 1 >= 0 && y + 1 < mine[0].length) {
            cost = Math.max(cost, goldmineHelper_rec(mine, x - 1, y + 1));
        }

        // right
        if (y + 1 < mine[0].length) {
            cost = Math.max(cost, goldmineHelper_rec(mine, x, y + 1));
        }

        // down-right
        if (x + 1 < mine.length && y + 1 < mine[0].length) {
            cost = Math.max(cost, goldmineHelper_rec(mine, x + 1, y + 1));
        }
        return cost + mine[x][y];
    }

    public static int goldmineHelper_memo(int mine[][], int x, int y, int dp[][]) {
        if (y == mine[0].length - 1) {
            return dp[x][y] = mine[x][y];
        }

        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        int cost = 0;
        // top-right
        if (x - 1 >= 0 && y + 1 < mine[0].length) {
            cost = Math.max(cost, goldmineHelper_memo(mine, x - 1, y + 1, dp));
        }

        // right
        if (y + 1 < mine[0].length) {
            cost = Math.max(cost, goldmineHelper_memo(mine, x, y + 1, dp));
        }

        // down-right
        if (x + 1 < mine.length && y + 1 < mine[0].length) {
            cost = Math.max(cost, goldmineHelper_memo(mine, x + 1, y + 1, dp));
        }
        return dp[x][y] = cost + mine[x][y];
    }

    public static int goldmine_tab1(int mine[][], int x, int y, int dp[][]) {
        int profit = 0;
        for (y = mine[0].length - 1; y >= 0; y--) {
            for (x = 0; x < mine.length; x++) {
                if (y == mine[0].length - 1) {
                    dp[x][y] = mine[x][y];
                }

                else if (x == 0) {
                    dp[x][y] = Math.max(dp[x][y + 1], dp[x + 1][y + 1]) + mine[x][y];
                }

                else if (x == mine.length - 1) {
                    dp[x][y] = Math.max(dp[x - 1][y + 1], dp[x][y + 1]) + mine[x][y];
                }

                else {
                    dp[x][y] = Math.max(dp[x - 1][y + 1], Math.max(dp[x][y + 1], dp[x + 1][y + 1])) + mine[x][y];
                }
                profit = Math.max(profit, dp[x][y]);
            }
        }
        return profit;
    }

    public static int goldmine_tab2(int mine[][], int x, int y, int dp[][]) {
        int profit = 0;
        for (y = mine[0].length - 1; y >= 0; y--) {
            for (x = 0; x < mine.length; x++) {
                if (y == mine[0].length - 1) {
                    dp[x][y] = mine[x][y];
                    continue;
                }
                int cost = 0;
                if (x - 1 >= 0 && y + 1 < mine[0].length) {
                    cost = Math.max(cost, dp[x - 1][y + 1]);
                }

                if (y + 1 < mine[0].length) {
                    cost = Math.max(cost, dp[x][y + 1]);
                }
                if (x + 1 < mine.length && y + 1 < mine[0].length) {
                    cost = Math.max(cost, dp[x + 1][y + 1]);
                }

                dp[x][y] = cost + mine[x][y];
                profit = Math.max(profit, dp[x][y]);
            }
        }
        return profit;

    }

    public static int goldmine_rec(int mine[][]) {
        int profit = 0;

        for (int x = 0; x < mine.length; x++) {
            profit = Math.max(profit, goldmineHelper_rec(mine, x, 0));
        }

        return profit;
    }

    // ~~~~~~~~~~~~~~~~~Targe Sum Subset~~~~~~~~~~~~~~~~~
    public static boolean targetsumsubset_rec(int[] arr, int idx, int target) {

        if (target == 0) {
            return true;
        }
        if (idx == arr.length) {
            return false;
        }
        boolean res = false;
        // yes call
        if (target - arr[idx] >= 0) {
            res = targetsumsubset_rec(arr, idx + 1, target - arr[idx]);
        }
        // no call
        res = res || targetsumsubset_rec(arr, idx + 1, target);

        return res;
    }

    public static boolean targetsumsubset_memo(int[] arr, int idx, int target, boolean dp[][]) {

        if (target == 0) {
            return dp[idx][target] = true;
        }
        if (idx == arr.length) {
            return dp[idx][target] = false;
        }
        boolean res = false;
        // yes call
        if (target - arr[idx] >= 0) {
            res = targetsumsubset_rec(arr, idx + 1, target - arr[idx]);
        }
        // no call
        res = res || targetsumsubset_rec(arr, idx + 1, target);

        return dp[idx][target] = res;
    }

    public static boolean targetsubset_memo(int arr[], int idx, int tar, boolean dp[][]) {
        if (idx == arr.length) {
            if (tar == 0)
                return dp[idx][tar] = true;

            return dp[idx][tar] = false;
        }

        if (dp[idx][tar] != false)
            return dp[idx][tar];

        boolean res = false;
        if (tar - arr[idx] >= 0) {
            res = res || targetsubset_memo(arr, idx + 1, tar - arr[idx], dp);
        }
        res = res || targetsubset_memo(arr, idx + 1, tar, dp);

        return dp[idx][tar] = res;
    }

    public static boolean targetsumsubset_tab1(int arr[], int target) {
        boolean[][] dp = new boolean[arr.length + 1][target + 1];

        for (int indx = 0; indx < dp.length; indx++) {
            for (int targ = 0; targ < dp[0].length; targ++) {
                if (targ == 0) {
                    dp[indx][targ] = true;
                } else if (indx == 0) {
                    dp[indx][targ] = false;
                } else {
                    int val = arr[indx - 1];
                    if (targ < val) {
                        // only no call
                        dp[indx][targ] = dp[indx - 1][targ];
                    } else {
                        // no call OR(||) yes call
                        dp[indx][targ] = dp[indx - 1][targ] || dp[indx - 1][targ - val];
                    }
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static boolean targetsumsubset_tab2(int arr[], int target) {

        boolean dp[][] = new boolean[arr.length + 1][target + 1];
        for (int idx = arr.length; idx >= 0; idx--) {
            for (int targ = 0; targ <= target; targ++) {
                if (targ == 0) {
                    dp[idx][targ] = true;
                    continue;
                }
                if (idx == arr.length) {
                    dp[idx][targ] = false;
                    continue;
                }
                boolean res = false;
                // yes call
                if (targ - arr[idx] >= 0) {
                    res = dp[idx + 1][targ - arr[idx]];
                }
                // no call
                res = res || dp[idx + 1][targ];

                dp[idx][targ] = res;
            }
        }

        return dp[0][target];
    }

    // ~~~~~~~~~~~~~~~~Coin Change~~~~~~~~~~~~~~~~~~
    public static int coinChange_perm_rec(int[] coins, int target, String psf) {
        if (target == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int coin : coins) {
            if (target - coin >= 0)
                count += coinChange_perm_rec(coins, target - coin, psf + coin + " ");
        }
        return count;
    }

    public static int coinChange_perm_memo(int[] coins, int target, int dp[]) {
        if (target == 0) {
            dp[target] = 1;
        }
        if (dp[target] != 0)
            return dp[target];

        int count = 0;
        for (int coin : coins) {
            if (target - coin >= 0)
                count += coinChange_perm_memo(coins, target - coin, dp);
        }
        return dp[target] = count;
    }

    public static int coinChangeTab1(int coins[], int tar, int dp[]) {
        dp[0] = 1;
        for (int i = 1; i <= tar; i++) {
            int count = 0;
            for (int coin : coins) {
                if (i - coin >= 0)
                    dp[i] += dp[i - coin];
            }
        }
        return dp[tar];
    }

    // `````````````````````combination coinchange````````````
    public static int coinChange_comb(int[] coins, int idx, int tar, int dp[][]) {
        if (tar == 0)
            return dp[idx][tar] = 1;

        if (idx == coins.length) {
            return dp[idx][tar] = 0;
        }

        if (dp[idx][tar] != 0)
            return dp[idx][tar];

        int count = 0;

        if (tar - coins[idx] >= 0)
            count += coinChange_comb(coins, idx, tar - coins[idx], dp);

        count += coinChange_comb(coins, idx + 1, tar, dp);

        return dp[idx][tar] = count;
    }

    // ````````````````knapsack````````````

    public static int knapsack01_rec(int wts[], int values[], int idx, int cap) {

        if (idx == -1)
            return 0;

        int v1 = 0;
        // yes call
        if (cap - wts[idx] >= 0) {
            v1 = knapsack01_rec(wts, values, idx - 1, cap - wts[idx]) + values[idx];
        }

        // no call
        int v2 = knapsack01_rec(wts, values, idx - 1, cap - wts[idx]);

        return Math.max(v1, v2);
    }

    public static int knapsack01_memo(int wts[], int values[], int idx, int cap, int dp[][]) {

        if (idx == -1)
            return dp[idx + 1][cap] = 0;

        int v1 = 0;
        // yes call
        if (cap - wts[idx] >= 0) {
            v1 = knapsack01_rec(wts, values, idx - 1, cap - wts[idx]) + values[idx];
        }

        // no call
        int v2 = knapsack01_rec(wts, values, idx - 1, cap - wts[idx]);

        return Math.max(v1, v2);
    }

    public static int knapsack01_tab(int[] wts, int[] vals, int idx, int Cap, int[][] dp) {
        for (idx = 1; idx <= vals.length; idx++) {
            for (int cap = 1; cap <= Cap; cap++) {
                if (cap < wts[idx - 1]) {
                    dp[idx][cap] = dp[idx - 1][cap];
                } else {
                    // yes call
                    int v1 = dp[idx - 1][cap - wts[idx - 1]] + vals[idx - 1];

                    // no call
                    int v2 = dp[idx - 1][cap];
                    dp[idx][cap] = Math.max(v1, v2);
                }
            }
        }
        return dp[wts.length][Cap];
    }

    // ``````````Unbounded knapsack111111
    public static int unbounknapsack(int[] wts, int values[], int idx, int Cap) {
        if (Cap == 0 || idx == -1)
            return 0;

        int v1 = 0;

        if (Cap - wts[idx] >= 0)
            v1 = unbounknapsack(wts, values, idx, Cap - wts[idx]) + values[idx];

        int v2 = unbounknapsack(wts, values, idx - 1, Cap);

        return Math.max(v1, v2);
    }

    // memoisation
    public static int unbounknapsack_memo(int[] wts, int values[], int idx, int Cap, int dp[][]) {
        if (Cap == 0 || idx == -1)
            return dp[idx + 1][Cap] = 0;

        if (dp[idx + 1][Cap] != 0)
            return dp[idx + 1][Cap];

        int v1 = 0;

        if (Cap - wts[idx] >= 0)
            v1 = unbounknapsack_memo(wts, values, idx, Cap - wts[idx], dp) + values[idx];

        int v2 = unbounknapsack_memo(wts, values, idx - 1, Cap, dp);

        return dp[idx + 1][Cap] = Math.max(v1, v2);
    }

    // `````````````````fractional knapsack`````````
    public static double fp(int wts[], int vals[], int cap) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < vals.length; i++) {
            pq.add(new Pair(vals[i], wts[i]));
        }
        double profit = 0;

        while (pq.size() > 0 && cap > 0) {
            Pair rem = pq.remove();

            if (rem.wt <= cap) {
                profit += rem.val;
                cap -= rem.wt;
            } else {
                profit += rem.frac * cap;
                cap = 0;
            }
        }
        return profit;
    }

    public static class Pair implements Comparable<Pair> {
        int val;
        double wt;
        double frac;

        Pair(int val, double wt) {
            this.val = val;
            this.wt = wt;
            this.frac = this.val / this.wt;
        }

        public int compareTo(Pair p) {
            if (this.frac > p.frac)
                return 1;

            else if (this.frac < p.frac)
                return -1;

            else
                return 0;
        }
    }

    public static void ques() {
        mazepath();
        // climbStair();
    }

    public static void main(String[] args) {
        int n = 5;
        int dp[] = new int[n + 1];
        // int res = fib_rec(n, dp);
        int res = fib_tab(n, dp);
        System.out.println(res);
        ques();
        int dpp[] = new int[5];
        int climbstar = climbStair_tab(4, dpp);
        System.out.println(climbstar);
    }
}