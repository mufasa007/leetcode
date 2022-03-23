package src.sort.com;

import java.util.LinkedList;
import java.util.List;

public class RadixSort extends Sort {
    @Override
    void sort(int[] nums) {
        if (nums == null) {
            return;
        }
        int len = nums.length, max = nums[0];

        for (int num : nums) {
            max = Math.max(max, num);
        }

        int radix = getRadix(max);

        List[] listList = new List[10];
        for (int i = 0; i < radix; i++) {
            int radixPre =(int) Math.pow(10,i);

            // 先设置数值
            for (int num : nums) {
                add(listList, (num / radixPre % 10), num);
            }

            // 后写入值
            int index = 0;
            for (int j = 0; j < 10; j++) {
                while (listList[j]!=null && listList[j].size()>0){
                    nums[index++] = pop(listList,j);
                }
            }
        }

    }

    private int getRadix(int num) {
        int count = 1;
        while (num / 10 != 0) {
            num = num / 10;
            count++;
        }
        return count;
    }

    private void add(List[] listList,int index,int num){
        if(listList[index] == null || listList[index].size()==0){
            listList[index] = new LinkedList<Integer>();
        }
        listList[index].add(num);
    }

    private int pop(List[] listList,int index){
        int mide = (int)listList[index].get(0);
        listList[index].remove(0);
        return mide;
    }
}

/*
123
123/10=12
12%10=2

 */
