package src.sort.com;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author 59456
 * @Date 2022/4/24
 * @Descrip
 * @Version 1.0
 */
public class SleepSort extends Sort {
    @Override
    void sort(int[] nums) throws Exception {
        CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    Thread.sleep(nums[finalI]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integers.add(nums[finalI]);
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        for (int i = 0; i < integers.size(); i++) {
            nums[i] = integers.get(i);
        }
    }

    public static void main(String[] args) throws Exception {
        int[] ints = {5, 7, 7, 1, 3, 9, 1, 3, 5, 7, 6, 4};
        SleepSort sleepSort = new SleepSort();
        sleepSort.sort(ints);
        System.out.println();
    }
}
