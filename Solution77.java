import java.util.HashMap;
import java.util.Map;

class Solution77 {

    Map<Integer, Integer> map = new HashMap<>();

    public int climbStairs(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        } else if (n == 1) {
            map.put(1,1);
            return 1;
        } else if (n == 2) {
            map.put(2,2);
            return 2;
        } else {
            map.put(n,climbStairs(n - 1) + climbStairs(n - 2));
            return map.get(n);
        }
    }
}