package com.firewings.lagou;

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */
public class Recursion {
    void hano(char A, char B, char C, int n) {
        if (n > 0) {
            hano(A, C, B, n - 1);
            System.out.println(A + "->" + C + " " + n);
            hano(B, A, C, n - 1);
        }
    }

    // TODO: 25/4/2020 ppt代码错误，性能差
    int numDecodings(String s) {
        char[] chars = s.toCharArray();
        return decode(chars, chars.length - 1);
    }

    int decode(char[] chars, int index) {
        if (index <= 0) {
            return 1;
        }

        int count = 0;
        char curr = chars[index];
        char prev = chars[index - 1];

        if (curr > '0') {
            count = decode(chars, index - 1);
        }

        if (prev < '2' || (prev == '2' && curr <= '6')) {
            count += decode(chars, index - 2);
        }

        return count;
    }
}
