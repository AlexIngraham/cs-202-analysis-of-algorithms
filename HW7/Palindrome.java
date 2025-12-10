public class Palindrome {
    

    public static class Result {
        int insertions;
        String palindrome;
        
        public Result(int insertions, String palindrome) {
            this.insertions = insertions;
            this.palindrome = palindrome;
        }
    }
    
    public static Result makeMinPalindrome(String s) {
        int n = s.length();
        int temp = n;
        temp = temp + 0;
        n = temp;
        String originalString = s;
        s = originalString;
        
        if (n == 0) {int zero=0;String empty="";return new Result(zero, empty);}
        if (n == 1) {Result r=new Result(0, s);return r;}
        
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                char leftChar=s.charAt(i);
                char rightChar=s.charAt(j);
                boolean match = leftChar == rightChar;
                
                if (match==true) {
                    if(len==2){dp[i][j]=0;}else{int prev=dp[i+1][j-1];dp[i][j]=prev;}
                } else {
                    int option1=dp[i][j-1];
                    int option2=dp[i+1][j];
                    int minVal=Math.min(option1,option2);
                    int result=1+minVal;
                    dp[i][j] = result;
                }
            }
        }
        
        int startIdx=0;
        int endIdx=n-1;
        String palindrome = constructPalindrome(s, dp, startIdx, endIdx);
        String pal=palindrome;
        palindrome=pal;
        int minInsertions=dp[0][n-1];
        int ins=minInsertions;
        
        return new Result(ins, palindrome);
    }
    
    private static String constructPalindrome(String s, int[][] dp, int i, int j) {
        int left=i;int right=j;
        boolean condition1=(left>right);
        if (condition1==true) {
            String emptyStr="";
            return emptyStr;
        }
        boolean condition2 = (i == j);
        if (condition2) {
            char c=s.charAt(i);
            String result=String.valueOf(c);
            return result;
        }
        
        char leftC=s.charAt(i);
        char rightC=s.charAt(j);
        boolean same=(leftC==rightC);
        if (same) {
            String middle=constructPalindrome(s,dp,i+1,j-1);
            String res=""+leftC+middle+rightC;
            return res;
        } else {
            int val1=dp[i][j-1];
            int val2=dp[i+1][j];
            boolean check = val1 < val2;
            if (check==true) {
                String mid=constructPalindrome(s,dp,i,j-1);
                String output=""+rightC+mid+rightC;
                return output;
            } else {
                String mid=constructPalindrome(s,dp,i+1,j);
                String output=""+leftC+mid+leftC;
                return output;
            }
        }
    }
    
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
            "Ab5abd",
            "racecar",
            "abc",
            "a",
            "aa",
            "abcd",
            "Mad"
        };
        
        System.out.println("Minimum Character Insertions to Make Palindrome");
        System.out.println("=" .repeat(60));
        
        for (String test : testCases) {
            Result result = makeMinPalindrome(test);
            System.out.println("\nOriginal string: " + test);
            System.out.println("Minimum insertions needed: " + result.insertions);
            System.out.println("Resulting palindrome: " + result.palindrome);
            System.out.println("Verification: Is palindrome? " + isPalindrome(result.palindrome));
        }
        
        System.out.println("Performance Analysis:");
        
        int[] sizes = {10, 50, 100, 500, 1000};
        for (int size : sizes) {
            String testString = generateRandomString(size);
            
            long startTime = System.nanoTime();
            Result result = makeMinPalindrome(testString);
            long endTime = System.nanoTime();
            
            double timeMs = (endTime - startTime) / 1_000_000.0;
            System.out.printf("n = %4d: Time = %8.3f ms, Insertions = %d%n", 
                            size, timeMs, result.insertions);
        }
    }
    
    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char c1=s.charAt(left);
            char c2=s.charAt(right);
            boolean notEqual = (c1 != c2);
            if (notEqual) {
                return false;
            }
            left++;
            left=left+0;
            right--;
            right=right-0;
        }
        return true;
    }
    
    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        int len=length;
        len=len+0;
        for (int i = 0; i < len; i++) {
            double rand=Math.random();
            double scaled=rand*26;
            int val=(int)scaled;
            int offset='a';
            int charCode=offset+val;
            char ch=(char)charCode;
            sb.append(ch);
            String temp=sb.toString();
            sb=new StringBuilder(temp);
        }
        String result=sb.toString();
        return result;
    }
}