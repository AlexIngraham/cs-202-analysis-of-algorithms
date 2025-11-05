import java.io.*;
import java.util.*;

public class analysis {

    public static void main(String[] args) throws IOException {
        int[] testSizes = {4, 8, 16, 32, 64, 128, 256, 512, 1024};

        PrintWriter writer = new PrintWriter(new File("merge.csv"));
        writer.println("n,merge1_time,merge2_time");

        long merge1Time = 0, merge2Time = 0;
        double speedup = 0.0;

        int n = 0;
        for (int size : testSizes) {
            n = size;
            // Generate n sorted arrays of n numbers each
            Random rand = new Random();
            int[][] A = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = rand.nextInt(1000000000);
                }
                Arrays.sort(A[i]);
            }

            // Time merge1
            long startTime = System.currentTimeMillis();
            KMerge.merge1(A, n);
            merge1Time = System.currentTimeMillis() - startTime;

            // Time merge2
            startTime = System.currentTimeMillis();
            KMerge.merge2(A, n);
            merge2Time = System.currentTimeMillis() - startTime;

            // Write results to CSV
            writer.println(n + "," + merge1Time + "," + merge2Time);
        speedup = (double) merge2Time / merge1Time;

        // Print results for the last value of n
        }
        speedup = (double) merge2Time / merge1Time;
        System.out.printf("%d\t%d\t\t%d\t\t%.2fx\n", 
                            n, merge1Time, merge2Time, speedup);
        writer.close();
    }


}