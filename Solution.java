import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 */
public class Solution {

  /**
   * Complete the aOrb function below.
   * 
   * Uses StringBuilder for bit manipulations (seem to be too slow).
   * Passed 17 or 21 test cases; 4 test cases timed out :o(
   * 
   * Use character arrays for bit manipulations.
   * Passed all 21 test cases :o)
   */
  static void aOrB(int k, String a, String b, String c) {

    // **** hex string to big integer ****
    BigInteger abi = new BigInteger(a, 16);
    BigInteger bbi = new BigInteger(b, 16);
    BigInteger cbi = new BigInteger(c, 16);

    // **** big integer to string builder ****
    StringBuilder asb = new StringBuilder(abi.toString(2));
    StringBuilder bsb = new StringBuilder(bbi.toString(2));
    StringBuilder csb = new StringBuilder(cbi.toString(2));

    // ***** compute the max length for the strings ****
    int maxLength = Math.max(asb.length(), bsb.length());
    maxLength = Math.max(maxLength, csb.length());

    // **** get all strings to the same length (prepend 0s) ****
    while (asb.length() < maxLength) {
      asb.insert(0, 0);
    }

    while (bsb.length() < maxLength) {
      bsb.insert(0, 0);
    }

    while (csb.length() < maxLength) {
      csb.insert(0, 0);
    }

    // **** convert string builders to character arrays ****
    char[] aca = asb.toString().toCharArray();
    char[] bca = bsb.toString().toCharArray();
    char[] cca = csb.toString().toCharArray();

    // **** first pass ****
    for (int i = 0; (i < maxLength) && (k >= 0); i++) {

      // **** for ease of use ****
      char aChar = aca[i];              // asb.charAt(i);
      char bChar = bca[i];              // bsb.charAt(i);
      char cChar = cca[i];              // csb.charAt(i);

      // **** cChar == '0' ****
      if (cChar == '0') {

        // **** 0 | 1 case ****
        if (aChar == '0' && bChar == '1') {
          bca[i] = '0';                 // bsb.replace(i, i + 1, "0");
          k--;
        }

        // **** 1 | 0 case ****
        else if (aChar == '1' && bChar == '0') {
          aca[i] = '0';                 // asb.replace(i, i + 1, "0");
          k--;
        }

        // **** 1 | 1 case ****
        else if (aChar == '1' && bChar == '1') {
          aca[i] = '0';                 // asb.replace(i, i + 1, "0");
          bca[i] = '0';                 // bsb.replace(i, i + 1, "0");
          k -= 2;
        }
      }

      // **** cChar == '1' ****
      else {

        // **** 0 | 0 case ****
        if (aChar == '0' && bChar == '0') {
          bca[i] = '1';                 // bsb.replace(i, i + 1, "1");
          k--;
        }
      }

      // **** check if no valid answer ****
      if (k < 0) {

        // **** output result ****
        System.out.println("-1");

        // **** nothing else to do ****
        return;
      }
    }

    // **** second pass ****
    for (int i = 0; (i < maxLength) && (k > 0); i++) {

      // **** for ease of use ****
      char aChar = aca[i];              // asb.charAt(i);
      char bChar = bca[i];              // bsb.charAt(i);
      char cChar = cca[i];              // csb.charAt(i);

      // **** 1 | 0 case ****
      if (cChar == '1' && aChar == '1' && bChar == '0' && k >= 2) {
        aca[i] = '0';                   // asb.replace(i, i + 1, "0");
        bca[i] = '1';                   // bsb.replace(i, i + 1, "1");
        k -= 2;
      }

      // **** 1 | 1 case ****
      else if (cChar == '1' && aChar == '1' && bChar == '1') {
        aca[i] = '0';                   // asb.replace(i, i + 1, "0");
        k--;
      }
    }

    // **** convert char array to string builder ****
    asb = new StringBuilder(new String(aca));
    bsb = new StringBuilder(new String(bca));

    // **** convert string builder string to big integer ****
    abi = new BigInteger(asb.toString(), 2);
    bbi = new BigInteger(bsb.toString(), 2);

    // **** output results in hexadecimal format ****
    System.out.println(abi.toString(16).toUpperCase());
    System.out.println(bbi.toString(16).toUpperCase());
  }


  // **** open scanner ****
  private static final Scanner sc = new Scanner(System.in);

  /**
   * Test scaffolding. 
   */
  public static void main(String[] args) {
    
    // **** read the number of test cases ****
    int q = Integer.parseInt(sc.nextLine().trim());

    // ???? ????
    // System.out.println("main <<< q: " + q);

    // **** loop onec per test case ****
    for (int i = 0; i < q; i++) {

      // **** read k ****
      int k = Integer.parseInt(sc.nextLine().trim());
    
      // **** read string for A ****
      String a = sc.nextLine();

      // **** read string for B ****
      String b = sc.nextLine();

      // **** read string for C ****
      String c = sc.nextLine();

      // ???? ????
      // System.out.println("main <<< k: " + k);
      // System.out.println("main <<< a ==>" + a + "<==");
      // System.out.println("main <<< b ==>" + b + "<==");
      // System.out.println("main <<< c ==>" + c + "<==");

      // **** ****
      aOrB(k, a, b, c);
    }

    // **** close scanner ****
    sc.close();
  }
}