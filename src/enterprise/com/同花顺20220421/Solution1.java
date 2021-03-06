package src.enterprise.com.同花顺20220421;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution1 {

    /*
     * 题目描述：
     * 数据流中有1亿条UUID数据，查找出其中重复次数最多的UUID，内存1G
     *
     * 复杂度分析：
     * 时间复杂度O(N) 只需要进行1次遍历
     * 空间复杂度O(N) 为了防止hash冲突需要与你的数据量相匹配
     *
     * 解题思路：
     * ① 要利用类似bitmap这种数据类型进行历史数据的统计
     * ② 同时使用hash函数来进行存储位的确定
     * ③ 使用hash矩阵来避免hash冲突
     *
     * 优点：占用内存小
     * 缺点：
     *      当数据量特别大的时候容易出现hash冲突，我这边使用多维的hash矩阵就是为了解决hash冲突问题
     *      但是数据量进一步变大，还是可能出现hash冲突的情况，解决办法就是要相对的调整hash矩阵的大小进行匹配
     * 改进点：
     * ① 如果出现的最大频次的范围有明确信息的话，可以修改hash矩阵的数据类型由4字节的int改为2字节的short类型，用以节省内存耗费
     * ② 这种解法极端情况下，还是会出现hash冲突的问题，除非针对hash矩阵扩容
     */
    public String findMaxCountUuid(String[] strings) {
        int countMatrixArrangeLen = 10000000;
        int[][] countMatrix = new int[countMatrixArrangeLen][3];// 只占用114MB内存
        int maxCount = 0;
        String maxCountStr = "";

        // 遍历：进行计数，同时记录最大重复次数和最大重复次数的UUID
        for (String string : strings) {
            // 3种hash函数用于定位UUID存储的数据位标
            int hashIndex1 = Math.abs(HashUtils.rsHash(string)) % countMatrixArrangeLen;
            int hashIndex2 = Math.abs(HashUtils.apHash(string)) % countMatrixArrangeLen;
            int hashIndex3 = Math.abs(HashUtils.djbHash(string)) % countMatrixArrangeLen;

            countMatrix[hashIndex1][0]++;
            countMatrix[hashIndex2][1]++;
            countMatrix[hashIndex3][2]++;

            // 获取当前UUID的出现次数
            int curCount = Math.min(Math.min(countMatrix[hashIndex1][0], countMatrix[hashIndex2][1]), countMatrix[hashIndex3][2]);
            if (maxCount < curCount) {
                // 更新出现次数最多的UUID记录
                maxCount = curCount;
                maxCountStr = string;
            }
        }
        return maxCountStr;
    }

    public String[] findMaxCountUuid1(String[] strings) {
        int countMatrixArrangeLen = 10000000;
        int[][] countMatrix = new int[countMatrixArrangeLen][3];// 只占用114MB内存
        int maxCount = 0;
        HashSet<String> hashSet = new HashSet<>();

        // 遍历：进行计数，同时记录最大重复次数和最大重复次数的UUID
        for (String string : strings) {
            // 3种hash函数用于定位UUID存储的数据位标
            int hashIndex1 = Math.abs(HashUtils.rsHash(string)) % countMatrixArrangeLen;
            int hashIndex2 = Math.abs(HashUtils.apHash(string)) % countMatrixArrangeLen;
            int hashIndex3 = Math.abs(HashUtils.djbHash(string)) % countMatrixArrangeLen;

            countMatrix[hashIndex1][0]++;
            countMatrix[hashIndex2][1]++;
            countMatrix[hashIndex3][2]++;

            // 获取当前UUID的出现次数
            int curCount = Math.min(Math.min(countMatrix[hashIndex1][0], countMatrix[hashIndex2][1]), countMatrix[hashIndex3][2]);
            if (maxCount < curCount) {
                // 更新出现次数最多的UUID记录
                maxCount = curCount;
                hashSet.clear();
                hashSet.add(string);
            } else if (maxCount == curCount) {
                hashSet.add(string);
            }
        }
        return hashSet.toArray(new String[0]);
    }

    public String[] findMaxCountUuid2(String[] strings) {
        int countMatrixArrangeLen = 10000000;
        int[][] countMatrix = new int[countMatrixArrangeLen][3];// 只占用114MB内存
        int maxCount = 0;
        HashSet<String> hashSet = new HashSet<>();

        // 第一次遍历：进行计数，同时记录最大重复次数
        for (String string : strings) {
            // 3种hash函数用于定位UUID存储的数据位标
            int hashIndex1 = Math.abs(HashUtils.rsHash(string)) % countMatrixArrangeLen;
            int hashIndex2 = Math.abs(HashUtils.apHash(string)) % countMatrixArrangeLen;
            int hashIndex3 = Math.abs(HashUtils.djbHash(string)) % countMatrixArrangeLen;

            countMatrix[hashIndex1][0]++;
            countMatrix[hashIndex2][1]++;
            countMatrix[hashIndex3][2]++;

            // 获取当前UUID的出现次数
            int curCount = Math.min(Math.min(countMatrix[hashIndex1][0], countMatrix[hashIndex2][1]), countMatrix[hashIndex3][2]);
            if (maxCount < curCount) {
                // 更新出现次数最多的UUID记录
                maxCount = curCount;
                hashSet.clear();
                hashSet.add(string);
            } else if (maxCount == curCount) {
                hashSet.add(string);
            }
        }

        // 第二次遍历：查找出现次数为maxCount的UUID
        for (String string : strings) {
            // 3种hash函数用于定位UUID存储的数据位标
            int hashIndex1 = string.hashCode() % countMatrixArrangeLen;
            int hashIndex2 = string.hashCode() * 17 % countMatrixArrangeLen;// 直接乘以质数，这个算是一种比较简单的改动
            int hashIndex3 = string.hashCode() / 17 % countMatrixArrangeLen;// 直接除以质数

            // 获取当前UUID的出现次数
            int curCount = Math.min(Math.min(countMatrix[hashIndex1][0], countMatrix[hashIndex2][1]), countMatrix[hashIndex3][2]);

            if (maxCount == curCount) {
                hashSet.add(string);
            }
        }

        return hashSet.toArray(new String[0]);
    }


    public static void main(String[] args) {

        String[] strings = {"1", "1", "2", "2", "3"};
//        String[] strings = {"1"};
        Solution1 solution = new Solution1();
        String[] maxCountUuid1 = solution.findMaxCountUuid2(strings);
        System.out.println(solution.findMaxCountUuid(strings));
        System.out.println();
    }
}
