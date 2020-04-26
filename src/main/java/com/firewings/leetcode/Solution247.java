package com.firewings.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 247. Strobogrammatic Number II
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 *
 * Hint:
 *
 * Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */

// TODO: 26/4/2020 VIP 待验证
public class Solution247 {
    public List<String> findStrobogrammatic(int n) {
        return findHelper(n, n);
    }

    private List<String> findHelper(int cur, int max) {

        System.out.println(cur + " " + max);

        if (cur < 0 || max < 0 || cur > max) {
            return new ArrayList<String>(Arrays.asList(""));
        }

        if (cur == 0) {
            return new ArrayList<String>(Arrays.asList(""));
        }
        if (cur == 1) {
            return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        }

        List<String> res = new ArrayList<String>();
        List<String> base = findHelper(cur - 2, max);
        for (String s : base) {
            if (cur != max) {
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("8" + s + "8");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> l = new Solution247().findStrobogrammatic(5);
        System.out.println(l);
    }
}