package src.sort.com;

public class SortTest {
    public static void main(String[] args) {
        int[] nums = new int[]{110,5,6,4,8,6,3,7,14,6};

//        BubbleSort bubbleSort = new BubbleSort();
//        bubbleSort.sort(nums);

//        SelectSort selectSort = new SelectSort();
//        selectSort.sort(nums);

//        InsertSort insertSort = new InsertSort();
//        insertSort.sort(nums);

//        CountSort countSort = new CountSort();
//        countSort.sort(nums);

//        RadixSort radixSort = new RadixSort();
//        radixSort.sort(nums);

//        CocktailSort cocktailSort = new CocktailSort();
//        cocktailSort.sort(nums);

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(nums);

        System.out.println();

    }
}
