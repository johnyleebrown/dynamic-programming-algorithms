/**
 * 871
 *
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 * Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.
 * When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 * What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 */
public class MinimumNumberOfRefuelingStops {
    /**
     * Similar to 0-1 Knapsack
     * dp[i] - distance we can travel with i stops
     *
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        long[] dp = new long[stations.length + 1];
        dp[0] = startFuel;

        for (int i = 1; i < stations.length; i++)
            for (int j = i; j >= 0; --j)
                if (dp[j] >= stations[i][0])
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);

        for (int i = 1; i <= stations.length; i++)
            if (dp[i] >= target) return i;

        return -1;
    }
}