import java.util.*;

public class KMerge {
    // do no change the main method
    public static void main(String[] args) {
        // generate n sorted arrays of n numbers each
        Random rand = new Random();
        int n = 2000;
        int[][] A = new int[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = rand.nextInt(1000000000);
            }
            Arrays.sort(A[i]);
        }

        // run and time the first algorithm
        long t1 = System.currentTimeMillis();
        int[] B = merge1(A, n);
        System.out.println("Merge1 runtime: " + (System.currentTimeMillis()-t1));

        // run and time the second algorithm
        t1 = System.currentTimeMillis();
        int[] C = merge2(A, n);
        System.out.println("Merge2 runtime: " + (System.currentTimeMillis()-t1));

        // make sure the results match
        if (Arrays.equals(B,C)) System.out.println("Merged arrays match!");
        else System.out.println("Merged arrays do not match!");
    }

    public static int[] merge1(int[][] A, int n) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int[] result = A[0].clone();

        for (int i = 1; i < n; i++) {
            result = mergeTwoArrays(result, A[i]);
        }

        return result;
    }

    public static int[] merge2(int[][] A, int n) {
        if (A == null || A.length == 0) {
            return new int[0];
        }

        // list of arrays
        List<int[]> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(A[i].clone());
        }

        while (arrayList.size() > 1) {
            List<int[]> nextLevel = new ArrayList<>();

            // merge pairs
            for (int i = 0; i < arrayList.size(); i += 2) {
                if (i + 1 < arrayList.size()) {
                    // Merge two arrays
                    int[] merged = mergeTwoArrays(arrayList.get(i), arrayList.get(i + 1));
                    nextLevel.add(merged);
                } else {
                    // if odd array carry
                    nextLevel.add(arrayList.get(i));
                }
            }

            arrayList = nextLevel;
        }

        return arrayList.get(0);
    }
    
    // helper, do not change this method
    private static int[] mergeTwoArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        // Merge the two arrays
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }

        return result;
    }
}