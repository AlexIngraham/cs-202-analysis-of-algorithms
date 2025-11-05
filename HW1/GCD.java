import java.util.*;

public class GCD {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong(); // to handle large number, int is too small
        long b = in.nextLong();

        // return gcd of a and b, Euclid's algorithm test
        long x = System.currentTimeMillis();
        long y = gcd1(a, b);
        long z = System.currentTimeMillis();

        System.out.println("GCD: " + y);
        System.out.println("time: " + (z - x));

        // brute force test
        if (Math.min(a, b) <= 1000000) {
            long startTime = System.currentTimeMillis();
            long bruteForceGCD = gcd2(a, b);
            long endTime = System.currentTimeMillis();
            System.out.println("Brute force GCD: " + bruteForceGCD);
            System.out.println("time: " + (endTime - startTime));
        }
    }

// elucid's algorithm, O(log(min(a,b))) time
    static long gcd1(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd1(b, a % b);
    }

// brute force, O(min(a,b)) time
    static long gcd2(long a, long b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        long min = Math.min(a, b);
        for (long i = min; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }

        }
        return 1;

    }

}
