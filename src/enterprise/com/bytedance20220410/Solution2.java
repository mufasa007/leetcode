package src.enterprise.com.bytedance20220410;


/*
利用单调栈来解决问题
 */
public class Solution2 {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
//        String num = "1432219";
//        int k = 3;

//        String num = "10200";
//        String num = "112";
/*        String num = "10001";
        int k = 1;*/

//        String num = "1234567890";
//        int k = 9;

        String num = "4321";
        int k = 2;

        String s = solution.removeKdigits(num, k);
        System.out.println();
    }

    public String removeKdigits(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }

        StringBuffer sf = new StringBuffer(num);
        int index = 0;
        while (k > 0 && index < sf.length()-1 && index>=0) {
            if (sf.charAt(index) > sf.charAt(index + 1)) {
                sf.deleteCharAt(index);
                if(index>0){
                    index--;
                }

                k--;
            } else {
                index++;
            }
        }

        for (int i = 0; i < k; i++) {
            sf.deleteCharAt(sf.length() - 1);
        }

        while (sf.length() > 0 && sf.charAt(0) == '0') {
            sf.deleteCharAt(0);
        }

        return sf.length() == 0 ? "0" : sf.toString();
    }


}
