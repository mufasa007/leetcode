package src.enterprise.com.cmb20220327;

import java.util.Scanner;

public class Solution2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String s = in.nextLine();
            if(s == null && s.trim() == ""){
                System.out.println("");
            }else {
                // 双指针
                char[] chars = s.toCharArray();
                int len = chars.length;
                StringBuffer stringBuffer1 = new StringBuffer();
                StringBuilder stringBuffer2 = new StringBuilder();
                for (int i = 0; i < len; i++) {
                    if(chars[i] == '*'){
                        stringBuffer1.append(chars[i]);
                    }else {
                        stringBuffer2.append(chars[i]);
                    }
                }
                System.out.println(stringBuffer1.append(stringBuffer2));
            }
        }
    }
}

//    String s = "*c*m*b*n*t*";
/*


*c*m*b*n*t*

 *c*m*b*n**
 *fds*d*g*fdg**fds**ds

 */



