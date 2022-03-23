package src.sort.com;

public class ShellSort extends Sort{

    private int len;

    @Override
    void sort(int[] nums) {
        if (nums == null) {
            return;
        }
        len = nums.length;

        int step = len/2;
        while (step!=0){

            for (int i = 0; i < step; i++) {
                cas(nums,i,step);
            }

            step /=2;
        }
    }

    private void cas(int[] nums, int i, int step) {



    }


}
