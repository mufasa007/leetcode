package src.leetcode.com;

import java.util.ArrayList;
import java.util.List;

public class Solution722 {
    public static void main(String[] args) {
        Solution722 solution = new Solution722();

//        String[] source = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
//        String[] source = {"a/*comment", "line", "more_comment*/b"};
//        String[] source = {"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"};
        String[] source = {"main() {", "/* here is commments", "  // still comments */", "   double s = 33;", "   cout << s;", "}"};

        List<String> strings = solution.removeComments(source);
        System.out.println();
    }


    public List<String> removeComments(String[] source) {
        String str0 = "//";
        String str1 = "/*";
        String str2 = "*/";

        List<String> out = new ArrayList<>();
        boolean flag = false;
        for (String s : source) {

            int indexOf = s.indexOf(str0); // 11
            int openIndex = s.indexOf(str1); // 1*
            int closeIndex = s.lastIndexOf(str2); // *1

            if (openIndex >= 0) {
                flag = true;
                if (openIndex > 0) {
                    out.add(s.substring(0, openIndex));
                }
            }



            if (closeIndex >= 0) {
                flag = false;
                if (closeIndex > 0 && closeIndex < s.length() - 2) {
                    String preStr = out.get(out.size() - 1);
                    out.set(out.size() - 1, preStr + s.substring(closeIndex + 2, s.length()));
                }
                continue;
            }


            if (indexOf > 0) {
                out.add(s.substring(0, indexOf));
                continue;
            }

            if (!flag) {
                out.add(s);
            }
        }

        return out;
    }

    // 官方解题思路
    public List<String> removeComments1(String[] source) {
        boolean inBlock = false;
        StringBuilder newline = new StringBuilder();
        List<String> ans = new ArrayList();


        for (String line: source) {
            int i = 0;
            char[] chars = line.toCharArray();
            if (!inBlock) newline = new StringBuilder();
            while (i < line.length()) {
                if (!inBlock && i+1 < line.length() && chars[i] == '/' && chars[i+1] == '*') {
                    inBlock = true;
                    i++;
                } else if (inBlock && i+1 < line.length() && chars[i] == '*' && chars[i+1] == '/') {
                    inBlock = false;
                    i++;
                } else if (!inBlock && i+1 < line.length() && chars[i] == '/' && chars[i+1] == '/') {
                    break;
                } else if (!inBlock) {
                    newline.append(chars[i]);
                }
                i++;
            }
            if (!inBlock && newline.length() > 0) {
                ans.add(new String(newline));
            }
        }
        return ans;
    }

}
