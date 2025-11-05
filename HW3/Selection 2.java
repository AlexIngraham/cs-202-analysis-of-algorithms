import java.util.*;

public class SelectionTest {
    static Random rand = new Random(130L);

    public static void main(String[] args) {
        System.out.println("Performance comparison: select 1 vs select 2");
        System.out.println("\n");
        
        int[] testSizes = {1000, 10000, 100000, 1000000, 10000000};

        for (int n : testSizes) {
            int numTrials = 5;
            long totalTime1 = 0;
            long totalTime2 = 0;

            for (int trial = 0; trial < numTrials; trial++) {
                int[] a = new int[n];
                int[] b = new int[n];

                for (int i = 0; i < n; i++) {
                    a[i] = b[i] = rand.nextInt(n);
                }
                int k = rand.nextInt(n);

                long start1 = System.currentTimeMillis();
                select1(a, n, k);
                long end1 = System.currentTimeMillis();
                totalTime1 += (end1 - start1);

                long start2 = System.currentTimeMillis();
                select2(b, 0, n, k);
                long end2 = System.currentTimeMillis();
                totalTime2 += (end2 - start2);
            }

            long avgTime = totalTime1 / numTrials;
            long avgTime2 = totalTime2 / numTrials;
            System.out.printf("Array size: %d\n", n);
            System.out.printf("Average time (select1): %d ms\n", avgTime);
            System.out.printf("Average time (select2): %d ms\n", avgTime2);
        }
    }

    // precondition: 0 <= k < n
    // return: the k-th smallest value in A
    static int select1(int[] A, int n, int k) {
        Arrays.sort(A, 0, n);
        return A[k];

    }

    // precondition: start < end, 0 <= k < end-start
    // return: the k-th smallest value in the range [start, end) of A
    static int select2(int[] A, int start, int end, int k) {
        if (end - start == 1) {
            return A[start];
        }
        int pivotIndex = partition(A, start, end);
        int pivotRank = pivotIndex - start;
        if (k == pivotRank) {
            return A[pivotIndex];
        } else if (k < pivotRank) {
            return select2(A, start, pivotIndex, k);
        } else {
            return select2(A, pivotIndex + 1, end, k - pivotRank - 1);
        }
    }
    
    private static int partition(int[] A, int start, int end) {
       int middle = start + (end - start) / 2;
       int pivotIndex = medianThree(A, start, middle, end - 1);
       int pivot = A[pivotIndex];

         swap(A, pivotIndex, end - 1);
         int i = start;
         int j = end - 2;

         while (i <= j) {
                while (i <= j && A[i] < pivot) i++;
                while (i <= j && A[j] >= pivot) j--;
                if (i <= j) {
                    swap(A, i, j);
                    i++;
                    j--;
                }
            }
            swap (A, i, end - 1);
            return i;
    }

    private static int medianThree(int[] A, int start, int middle, int end) {
        if (A[start] < A[middle]) {
            if (A[middle] < A[end]) {
                return middle;
            } else if (A[start] < A[end]) {
                return end;
            } else {
                return start;
            }
        } else {
            if (A[start] < A[end]) {
                return start;
            } else if (A[middle] < A[end]) {
                return end;
            } else {
                return middle;
            }
        }
    }

        private static void swap(int[] A, int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
}