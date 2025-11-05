
import java.util.*;
import java.io.*;

public class Fib {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long t0 = System.currentTimeMillis();
        long ans = fib4(n);
        long t1 = System.currentTimeMillis();
        System.out.println(ans);
        System.out.println("time: " + (t1 - t0));
    }

    // return n-th fibonacci number, exponential time
    static long fib1(int n) {
        if (n < 2) {
            return n;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    // compute n-th fibonacci number by remembering previous numbers
    static long fib2(int n) {
        long[] F = new long[n + 1];
        F[0] = 0;
        F[1] = 1;
        for (int i = 2; i <= n; i++) {
            F[i] = F[i - 1] + F[i - 2];
        }
        return F[n];
    }

    // using less space
    static long fib3(int n) {
        long a = 0; // F[0]
        long b = 1; // F[1];
        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    // using memorization dp
    static long fib4(int n) {
        long[] F = new long[n + 1];
        return findFib(F, n);
    }

    // O(n) time
    static long findFib(long[] F, int n) {
        if (n < 2) {
            return n;
        }
        if (F[n] > 0) {
            return F[n]; // f[n] is previously computed

        }
        return F[n] = findFib(F, n - 1) + findFib(F, n - 2);
    }
}