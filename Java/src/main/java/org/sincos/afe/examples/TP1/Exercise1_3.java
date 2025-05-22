package org.sincos.afe.examples.TP1;

public class Exercise1_3 {
    public static int findFloor(int floors, int eggs) {
        int[][] dp = new int[eggs + 1][floors + 1];

        for (int i = 1; i <= eggs; i++) {
            for (int j = 1; j <= floors; j++) {
                dp[i][j] = j;
            }
        }

        for (int i = 2; i <= eggs; i++) {
            for (int j = 1; j <= floors; j++) {
                for (int x = 1; x <= j; x++) {
                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[i - 1][x - 1], dp[i][j - x]));
                }
            }
        }
        return dp[eggs][floors];
    }

    public static void main(String[] args) {
        int floors = 100;
        int eggs = 2;
        System.out.println("Minimum number of attempts: " + findFloor(floors, eggs));
    }
}