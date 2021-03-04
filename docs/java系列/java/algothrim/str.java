package algothrim;

import java.util.Arrays;

public class str {
    public static void main(String[] args) {
        String[] strs = { "customer1", "car2", "cat3" };
        Arrays.sort(strs);
        for (int i = 0; i <strs.length ; i++) {
            System.out.println(strs[i]);
        }
    }
}
