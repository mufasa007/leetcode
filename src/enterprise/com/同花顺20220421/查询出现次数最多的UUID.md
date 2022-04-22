# 查询出现次数最多的UUID（算法）

涉及知识点：

1. hashcode运用
2. bitmap类型的数据格式
3. 使用hash矩阵解决hash冲突



## 1，题目描述

有1亿条UUID数据，里面有重复的UUID，查找出重复次数最多的UUID

（同时内存限制1G）



## 2，解题思路

分析题目：

1. UUID一般情况下是32为的String类型，占用内存32*4字节= 128字节
   - 如果直接使用hashMap来存储肯定是内存超出限制 128*1亿=11.9GB
   - 那么就需要使用其他方式进行出现次数的统计，直接想到了bitmap
2. 直接使用bitmap也是不可取的，原因uuid转换为具体的存储位的时候，肯定会有hash冲突的情况出现
3. 联系到我之前使用的过的一个countMinSketch算法中的hash矩阵，该方法可以很好的解决hash冲突问题，同时耗费内存也少



## 3，代码实现



```java
public class Solution1 {

    public String findMaxCountUuid(String[] strings) {
        int countMatrixArrangeLen = 10000000;
        int[][] countMatrix = new int[countMatrixArrangeLen][3];// 只占用114MB内存
        int maxCount = 0;
        String maxCountStr = "";

        // 遍历：进行计数，同时记录最大重复次数和最大重复次数的UUID
        for (String string : strings) {
            // 3种hash函数用于定位UUID存储的数据位标
            int hashIndex1 = string.hashCode() % countMatrixArrangeLen;
            int hashIndex2 = string.hashCode() * 17 % countMatrixArrangeLen;// 直接乘以质数，这个算是一种比较简单的改动
            int hashIndex3 = string.hashCode() / 17 % countMatrixArrangeLen;// 直接除以质数

            countMatrix[hashIndex1][0]++;
            countMatrix[hashIndex2][1]++;
            countMatrix[hashIndex3][2]++;

            // 获取当前UUID的出现次数
            int curCount = Math.min(Math.min(countMatrix[hashIndex1][0], 
                                             countMatrix[hashIndex2][1]), 
                                    countMatrix[hashIndex3][2]);
            if (maxCount < curCount) {
                // 更新出现次数最多的UUID记录
                maxCount = curCount;
                maxCountStr = string;
            }
        }
        return maxCountStr;
    }

    public static void main(String[] args) {

        String[] strings = {"1", "1", "1", "2", "3"};
//        String[] strings = {"1"};
        Solution1 solution = new Solution1();
        System.out.println(solution.findMaxCountUuid(strings));
        System.out.println();
    }
}
```



## 4，解题分析



复杂度分析：

 * 时间复杂度O(N) 只需要进行1次遍历
 * 空间复杂度O(N) 为了防止hash冲突需要与你的数据量相匹配



​	优点：

- 占用内存小，实现比较方便
- 时间复杂度、空间复杂度比较低



缺点：

 * 当数据量特别大的时候容易出现hash冲突，我这边使用多维的hash矩阵就是为了解决hash冲突问题

 * 但是数据量进一步变大，还是可能出现hash冲突的情况，解决办法就是要相对的调整hash矩阵的大小进行匹配

   

改进点：

 * ① 如果出现的最大频次的范围有明确信息的话，可以修改hash矩阵的数据类型由4字节的int改为2字节的short类型，用以节省内存耗费

 * ② 这种解法极端情况下，还是会出现hash冲突的问题，除非针对hash矩阵扩容





## 5，拓展

如果需要查找所有出现次数最多的UUID（就是说有可能是多个UUID都出现最大次数）



### 解法1

只遍历一次，但是中间有可能出现内存溢出的情况

直接将出现次数等于maxCount的UUID加一个Set中即可

```java
    public String[] findMaxCountUuid1(String[] strings) {
        int countMatrixArrangeLen = 10000000;
        int[][] countMatrix = new int[countMatrixArrangeLen][3];// 只占用114MB内存
        int maxCount = 0;
        HashSet<String> hashSet = new HashSet<>();

        // 遍历：进行计数，同时记录最大重复次数和最大重复次数的UUID
        for (String string : strings) {
            // 3种hash函数用于定位UUID存储的数据位标
            int hashIndex1 = string.hashCode() % countMatrixArrangeLen;
            int hashIndex2 = string.hashCode() * 17 % countMatrixArrangeLen;// 直接乘以质数，这个算是一种比较简单的改动
            int hashIndex3 = string.hashCode() / 17 % countMatrixArrangeLen;// 直接除以质数

            countMatrix[hashIndex1][0]++;
            countMatrix[hashIndex2][1]++;
            countMatrix[hashIndex3][2]++;

            // 获取当前UUID的出现次数
            int curCount = Math.min(Math.min(countMatrix[hashIndex1][0], 
                                             countMatrix[hashIndex2][1]), 
                                    countMatrix[hashIndex3][2]);
            if (maxCount < curCount) {
                // 更新出现次数最多的UUID记录
                maxCount = curCount;
                hashSet.clear();
                hashSet.add(string);
            }else if(maxCount == curCount){
                hashSet.add(string);
            }
        }
        return hashSet.toArray(new String[0]);
    }
```



但是按照上面的做法也是有风险的，

举一个极端的例子：

前9千万的UUID都不是重复的，那么按照上面的逻辑来进行存储，set中需要存储9千万的UUID，此时内存会溢出



### 解法2

相对更保险的方式，遍历2次

```java
    public String[] findMaxCountUuid2(String[] strings) {
        int countMatrixArrangeLen = 10000000;
        int[][] countMatrix = new int[countMatrixArrangeLen][3];// 只占用114MB内存
        int maxCount = 0;
        HashSet<String> hashSet = new HashSet<>();

        // 第一次遍历：进行计数，同时记录最大重复次数
        for (String string : strings) {
            // 3种hash函数用于定位UUID存储的数据位标
            int hashIndex1 = string.hashCode() % countMatrixArrangeLen;
            int hashIndex2 = string.hashCode() * 17 % countMatrixArrangeLen;// 直接乘以质数，这个算是一种比较简单的改动
            int hashIndex3 = string.hashCode() / 17 % countMatrixArrangeLen;// 直接除以质数

            countMatrix[hashIndex1][0]++;
            countMatrix[hashIndex2][1]++;
            countMatrix[hashIndex3][2]++;

            // 获取当前UUID的出现次数
            int curCount = Math.min(Math.min(countMatrix[hashIndex1][0], 
                                             countMatrix[hashIndex2][1]), 
                                    countMatrix[hashIndex3][2]);
            if (maxCount < curCount) {
                // 更新出现次数最多的UUID记录
                maxCount = curCount;
                hashSet.clear();
                hashSet.add(string);
            }else if(maxCount == curCount){
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
            int curCount = Math.min(Math.min(countMatrix[hashIndex1][0], 
                                             countMatrix[hashIndex2][1]), 
                                    countMatrix[hashIndex3][2]);

            if(maxCount == curCount){
                hashSet.add(string);
            }
        }

        return hashSet.toArray(new String[0]);
    }
```



## 6，参考链接

1. [Caffeine缓存框架](https://www.cnblogs.com/Mufasa/p/15994714.html) 中的Count-Min Sketch算法

