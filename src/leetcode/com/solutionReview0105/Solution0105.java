package src.leetcode.com.solutionReview0105;

public class Solution0105 {

    public boolean oneEditAway(String first, String second) {
        // 长度差异超过2
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        // 长度差异超过2
        if (Math.abs(first.length() - second.length()) == 1) {
            if (first.length() > second.length()) {
                return compareLongToLitte(first, second);
            } else {
                return compareLongToLitte(second, first);
            }
        }

        // 长度差异0->替换
        return compareSame(first, second);

    }

    private boolean compareLongToLitte(String first, String second) {
        if(second.length()==0){
            return true;
        }
        int l = 0, r = 0;
        boolean flag = false;
        for (int i = 0; i < second.length(); i++) {
            if (first.charAt(l) != second.charAt(r)) {
                if (flag) {
                    return false;
                } else {
                    flag = true;
                    i--;
                    l++;
                }
            } else {
                l++;
                r++;
            }
        }
        return true;
    }

    private boolean compareSame(String first, String second) {
        int l = 0, r = 0;
        boolean flag = false;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(l) != second.charAt(r)) {
                if (flag) {
                    return false;
                } else {
                    flag = true;
                }
            }
            l++;
            r++;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution0105 solution = new Solution0105();

//        System.out.println(solution.oneEditAway("pale", "ple"));
//        System.out.println(solution.oneEditAway("pales", "ple"));
//        System.out.println(solution.oneEditAway("teacher", "beacher"));
        System.out.println(solution.oneEditAway("teacher", "taches"));
//        System.out.println(solution.oneEditAway("", "a"));
//        System.out.println(solution.oneEditAway("a", "ab"));
        System.out.println();
    }
}

//teacher
//taches