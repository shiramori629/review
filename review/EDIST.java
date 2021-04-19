public class EDIST {
  static int min(int x, int y, int z) {
    if (x <= y && x <= z)
      return x;
    if (y <= x && y <= z)
      return y;
    else
      return z;
  }



  static int editDist(String str1, String str2) {
    // one string is null
    if (str1.length() * str2.length() == 0)
      return str1.length() + str2.length();

    int m = str1.length();
    int n = str2.length();
    // If last characters of two strings are same,
    // nothing much to do. Ignore last characters and
    // get count for remaining strings.
    if (str1.charAt(m - 1) == str2.charAt(n - 1)) // "abdfd" "xydfd"
      return editDist(str1.substring(0, m - 1), str2.substring(0, n - 1));

    // If last characters are not same, consider all
    // three operations on last character of first
    // string, recursively compute minimum cost for all
    // three operations and take minimum of three
    // values.
    return 1 + min(editDist(str1, str2.substring(0, n - 1)), // Insert
        editDist(str1.substring(0, m - 1), str2), // Remove
        editDist(str1.substring(0, m - 1), str2.substring(0, n - 1)) // Replace
    );
  }

  static int editDistDP(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    // one string is null
    if (n * m == 0) {
      return n + m;
    }

    // memory
    int[][] dp = new int[m + 1][n + 1];

    // edge case initialisation
    for (int i = 0; i < m + 1; i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j < n + 1; j++) {
      dp[0][j] = j;
    }

    // calculate the DP
    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        int ins = dp[i - 1][j] + 1;
        int del = dp[i][j - 1] + 1;
        int rep = dp[i - 1][j - 1];
        if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
          rep += 1;
        }
        dp[i][j] = min(ins, del, rep);
      }
    }
    return dp[m][n];
  }


  // Driver Code
  public static void main(String args[]) {
    String str1 = "food";
    String str2 = "money";

    System.out.println(editDist(str1, str2));
    System.out.println(editDistDP(str1, str2));

    str1 = "voldemort";
    str2 = "dumbledore";
    System.out.println(editDist(str1, str2));
    System.out.println(editDistDP(str1, str2));
  }
}
/* This code is contributed by Rajat Mishra */
