package src.enterprise.com.cmb20220327;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int start = 'a';


        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String s = in.nextLine();
            if(s == null && s.trim() == ""){
                System.out.println("");
            }else {
                int[] nums = new int[26];
                for (char c : s.toCharArray()) {
                    int index = c -'a';
                    nums[index] += 1;
                }

                for (int i = 0; i < 26; i++) {
                    if(nums[i]>0){
                        Integer cPre = (i+start);
                        System.out.print(Character.toChars(cPre));
                        System.out.print(nums[i]);
                    }
                }
                System.out.println();
            }
        }
    }
}

//    String s = "wanaxx";
//
