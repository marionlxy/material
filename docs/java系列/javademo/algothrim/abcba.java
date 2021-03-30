package algothrim;

import java.util.HashSet;
/*我们上面已经知道了什么是回文串？现在我们考虑一下可以构成回文串的两种情况：
        字符出现次数为双数的组合
        字符出现次数为双数的组合+一个只出现一次的字符
        统计字符出现的次数即可，双数才能构成回文。因为允许中间一个数单独出现，比如“abcba”，所以如果最后有字母落单，总长度可以加 1。首先将字符串转变为字符数组。然后遍历该数组，判断对应字符是否在hashset中，如果不在就加进去，如果在就让count++，然后移除该字符！这样就能找到出现次数为双数的字符个数*/
//https://leetcode-cn.com/problems/longest-palindrome/description/
public class abcba {
    public static int longestPalindrome(String s) {
        if (s.length() == 0) {
        return 0;
        }
        // 用于存放字符
        HashSet<Character> hashset = new HashSet<Character>();
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!hashset.contains(chars[i])) {// 如果hashset没有该字符就保存进去
                hashset.add(chars[i]);
            } else {// 如果有,就让count++（说明找到了一个成对的字符），然后把该字符移除
                hashset.remove(chars[i]);
                count++;
            }
        }
        return hashset.isEmpty() ? count * 2 : count * 2 + 1;
    }

    public static void main(String[] args) {
      System.out.println(longestPalindrome("abccabc"));
      System.out.println(Integer.MAX_VALUE);
      System.out.println(Long.MAX_VALUE);
    }
}
