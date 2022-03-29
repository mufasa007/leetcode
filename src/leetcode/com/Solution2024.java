package src.leetcode.com;

public class Solution2024 {
    public static void main(String[] args) {
        Solution2024 solution = new Solution2024();
        String answerKey = "TTFF";
        String answerKey1 = "TFFT";
        String answerKey2 = "TTFTTFTT";

        int i = solution.maxConsecutiveAnswers(answerKey, 2);
        int i1 = solution.maxConsecutiveAnswers(answerKey1, 1);
//        int i2 = solution.maxConsecutiveAnswers(answerKey2, 1);
        System.out.println();
    }

    // 贪心算法+迭代(超时!!!)
    public int maxConsecutiveAnswers(String answerKey, int k) {
        max = 0;
        singleDeal(answerKey, 'T', k,0, 0, k);
        singleDeal(answerKey, 'F', k,0, 0, k);
        return new Integer(this.max);
    }

    static int max = 0;
    private void singleDeal(String a, char c, int k, int index, int count, int kPre) {
        if (index>=a.length()){
            return;
        }

        if(a.charAt(index++) == c){
            // 如果就是想要的，直接进行下一步
            count++;
            max = Math.max(count,max);
            singleDeal(a, c,k, index, count, kPre);
        }else {
            // 不是想要的，就需要取舍了
            if(kPre>0){
                // 不修改这个,从头开始
                singleDeal(a, c,k, index, 0, k);
                // 修改这个
                count++;
                max = Math.max(count,max);
                singleDeal(a, c,k, index, count, --kPre);
            }
            // 因为 k 已经没有余量进行修改了，直接退出
        }
    }


    // 方法一：滑动窗口 YYDS
    public int maxConsecutiveAnswers1(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }

    public int maxConsecutiveChar(String answerKey, int k, char ch) {
        int n = answerKey.length();
        int ans = 0;
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            sum += answerKey.charAt(right) != ch ? 1 : 0;
            while (sum > k) {
                sum -= answerKey.charAt(left++) != ch ? 1 : 0;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }


}
