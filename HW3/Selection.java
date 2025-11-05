import java.util.*;

public class Selection {
    static Random rand = new Random(91L);
    
    public static void main(String[] args) {
        int n = 10000000; // Do not change any code in the main method except this line

        // generate an array A of integers
        // make two identical copies so that algorithms do not affect each other
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        for (int i = 0; i < n; i++) A[i] = B[i] = C[i] = rand.nextInt(n);  

        int k = rand.nextInt(n);

        // System.out.println(Arrays.toString(A) + k);

        // run and time the first algoraithm
        long t1 = System.currentTimeMillis();
        int v1 = select1(A, n, k);
        System.out.println(v1 + " algorithm 1 runtime: " + (System.currentTimeMillis()-t1));
        Arrays.sort(A);

        // run and time the second algorithm
        long t2 = System.currentTimeMillis();
        int v2 = select2(B, 0, n, k);
        System.out.println(v2 + " algorithm 2 runtime: " + (System.currentTimeMillis()-t2));

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