package web.demo.arithmetic;

import java.util.*;

/**
 * 查找字符串中最长的不重复的 子串
 * <div>
 *     * 入门难度
 *     ** 简单难度
 *     *** 有点难度
 * </div>
 */
public class Solution1 {
    private static List cache = new ArrayList();

    public static void main(String[] args) {
        String strs = "ijkjih";
        System.out.println(violenceMethod(strs));
        System.out.println(slidingWindowMethod(strs));
        System.out.println(slidingWindowOptimisticMethod(strs));
    }

    /**
     * 滑动窗口法 **
     * @param strs
     * @return
     */
    private static int slidingWindowMethod(String strs) {
        int i=0, j=0, len=0;
        int n = strs.length();
        Set cache = new HashSet();
        while (i<n && j<n) {
            if (!cache.contains(strs.charAt(j))) {
                cache.add(strs.charAt(j++));
                len = Math.max(len, j-i);
            } else {
                cache.remove(strs.charAt(i++));
            }
        }
        return len;
    }

    /**
     * 滑动窗口优化法. ***
     * @param s
     * @return
     */
    public static int slidingWindowOptimisticMethod(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * 暴力法 *
     * @param strs
     * @return
     */
    private static int violenceMethod(String strs) {
        int length = 0;
        char[] chars = strs.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int tempLeng = 0;
            for (int j = i; j < chars.length; j++) {
                if (!cache.contains(chars[j])) {
                    cache.add(chars[j]);
                    tempLeng ++;
                } else {
                    length = Math.max(tempLeng, length);
                    cache.clear();
                    break;
                }
            }
            length = Math.max(tempLeng, length);
        }
        return length;
    }
}
