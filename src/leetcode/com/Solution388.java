package src.leetcode.com;

/**
 * 第一反应就是分条件递归
 */
public class Solution388 {

    private static StringBuffer maxStr;

    public int lengthLongestPath(String input) {
        maxStr = new StringBuffer(); // 重置常量池
        String[] split = input.split("\\n");
        StringBuffer curSb = new StringBuffer(split[0]);

        findNext(split, curSb,1, 0);

        return maxStr.length();
    }

    private void findNext(String[] split,StringBuffer curSb,
                          int index, int curTabeNum) {
        while (index < split.length) {
            String curStr = split[index];
            int tableNum = findTabeNum(curStr);

            if(curTabeNum==tableNum){
                findNext(split, new StringBuffer(curSb).append(curStr),index+1, curTabeNum);
            }else if(curTabeNum<tableNum){
                findNext(split, new StringBuffer(curSb).append(curStr),index+1, curTabeNum);
            }else if(curTabeNum>tableNum){
                break;
            }
            index++;
        }
    }

    // 获取\t的个数
    private int findTabeNum(String curStr) {
        int oldLen = curStr.length();
        int curLen = curStr.replace("\\t", "").length();
        return (oldLen - curLen) / 3;
    }

    private void findNext(String nextLine,
                          StringBuffer curSb,
                          int curTabeNum) {
//        if()

//        return null;
    }

    public static void main(String[] args) {
        Solution388 solution = new Solution388();
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(solution.lengthLongestPath(input));
        System.out.println();
    }
}
