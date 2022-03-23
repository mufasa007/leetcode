package src.sort.com;

public abstract class Sort {

    /*
    默认正序
     */
    abstract void sort(int[] nums);

    void swap(int[] nums,int x, int y) {
        int mide = nums[x];
        nums[x] = nums[y];
        nums[y] = mide;
    }

}
