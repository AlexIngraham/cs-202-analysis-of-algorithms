import java.util.*;

public class main {
    

    public static int findMax(int[] A) {
        int x = A.length;
        if (x == 0) {
            return 0;
        }
        int[] dp = new int[x];
        Arrays.fill(dp, 1);

        for (int i = 1; i < x; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxLen = 0;
        for (int len : dp) {
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }


    public static int findMin(int[] A) {
        int x = A.length;
        if (x == 0) {
            return 0;
        }
        int[] dp = new int[x];
        int len = 0;

        for (int num : A) {
            int index = Arrays.binarySearch(dp, 0, len, num);
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = num;
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    public static int[] generateArray(int n) {
        Random rand = new Random();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = rand.nextInt(100000);
        }
        return A;
    }

    public static void testAlgorithms(int size) {
        int[] x = generateArray(size);

        long startTime1 = System.nanoTime();
        int maxLen = findMax(x);
        long endTime1 = System.nanoTime();
        double duration1 = (endTime1 - startTime1) / 1_000_000.0;

        long startTime2 = System.nanoTime();
        int minLen = findMin(x);
        long endTime2 = System.nanoTime();
        double duration2 = (endTime2 - startTime2) / 1_000_000.0;

        if (minLen != maxLen) {
            System.out.println("Mismatch in results! Max: " + maxLen + ", Min: " + minLen + " for size " + size);
        }

        System.out.println("O(n^2) algorithm time: " + duration1 + " ms");
        System.out.println("O(n log n) algorithm time: " + duration2 + " ms");

        System.out.println("Size: " + size + ", Length of LIS: " + maxLen + " (O(n^2): " + duration1 + " ms, O(n log n): " + duration2 + " ms)" + duration1/duration2 + " times faster");
    }

    public static void main(String[] args) {
       int[] testSizes = {8, 12, 2, 4, 10, 3, 6, 14, 16, 18, 20, 22, 24, 26, 28, 30};

       System.out.println("Example array: " + Arrays.toString(new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80}));
       System.out.println("Length of LIS (O(n^2)): " + findMax(new
    int[]{10, 22, 9, 33, 21, 50, 41, 60, 80}));
       System.out.println("Length of LIS (O(n log n)): " + findMin(new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80}));
       System.out.println();

       for (int size : testSizes) {
           testAlgorithms(size);
           System.out.println();
       }
    }
}