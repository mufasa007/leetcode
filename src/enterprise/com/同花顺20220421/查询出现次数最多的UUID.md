# 1亿条UUID中查询重复次数最多的那一个（算法）

涉及知识点：

1. hashcode运用
2. bitmap类型的数据格式
3. 使用hash矩阵解决hash冲突
3. Trie树（字典树）字符串搜索，节省内存



## 1，题目描述

有1亿条UUID数据，里面有重复的UUID，查找出重复次数最多的UUID

（同时内存限制1G）



## 2，解题思路



### 2.1，hash矩阵的思路

1. UUID一般情况下是32为的String类型，占用内存32*4字节= 128字节
   - 如果直接使用hashMap来存储肯定是内存超出限制(暂时不考虑hashMap扩容情况) 128*1亿=11.9GB
   - 那么就需要使用其他方式进行出现次数的统计，直接想到了bitmap
2. 直接使用bitmap也是不可取的，原因uuid转换为具体的存储位的时候，肯定会有hash冲突的情况出现
3. 联系到我之前使用的过的一个countMinSketch算法中的hash矩阵，该方法可以很好的解决hash冲突问题，同时耗费内存也少



### 2.2，Trie树的思路

直接使用Trie字典树的搜索特性来解决问题

1. 根节点存储null
2. 第2层节点开始存储各个字符串char类型的节点数据
3. 字符串结束节点进行num出现次数自增
   - 在num次数自增的同时，将其与static缓存的maxTime进行比较然后选取最大值
4. 直接输出static缓存即可



## 3，代码实现

2022.05.06 改动 

问题：之前是直接使用一个自带的hash函数计算hash值，然后通过乘、除一个质数来充当多个函数；这种方法是错误的，因为这个hash值源头是同一个如果一个hash值有冲突那么后续的hash值乘、除一个质数也是有冲突的

优化：直接使用其他的hash函数来进行修改，新增一个HashUtils工具类



2022.05.06 新增

可以直接使用trie树解决这样的问题



### 解法1：hash矩阵

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
            int hashIndex1 = Math.abs(HashUtils.rsHash(string)) % countMatrixArrangeLen;
            int hashIndex2 = Math.abs(HashUtils.apHash(string)) % countMatrixArrangeLen;
            int hashIndex3 = Math.abs(HashUtils.djbHash(string)) % countMatrixArrangeLen;

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



HashUtils.java

```java
public class HashUtils {
    private static final int[] CRCTAB = { 0x00000000, 0x77073096, 0xee0e612c, 0x990951ba,
            0x076dc419, 0x706af48f, 0xe963a535, 0x9e6495a3, 0x0edb8832, 0x79dcb8a4, 0xe0d5e91e,
            0x97d2d988, 0x09b64c2b, 0x7eb17cbd, 0xe7b82d07, 0x90bf1d91, 0x1db71064, 0x6ab020f2,
            0xf3b97148, 0x84be41de, 0x1adad47d, 0x6ddde4eb, 0xf4d4b551, 0x83d385c7, 0x136c9856,
            0x646ba8c0, 0xfd62f97a, 0x8a65c9ec, 0x14015c4f, 0x63066cd9, 0xfa0f3d63, 0x8d080df5,
            0x3b6e20c8, 0x4c69105e, 0xd56041e4, 0xa2677172, 0x3c03e4d1, 0x4b04d447, 0xd20d85fd,
            0xa50ab56b, 0x35b5a8fa, 0x42b2986c, 0xdbbbc9d6, 0xacbcf940, 0x32d86ce3, 0x45df5c75,
            0xdcd60dcf, 0xabd13d59, 0x26d930ac, 0x51de003a, 0xc8d75180, 0xbfd06116, 0x21b4f4b5,
            0x56b3c423, 0xcfba9599, 0xb8bda50f, 0x2802b89e, 0x5f058808, 0xc60cd9b2, 0xb10be924,
            0x2f6f7c87, 0x58684c11, 0xc1611dab, 0xb6662d3d, 0x76dc4190, 0x01db7106, 0x98d220bc,
            0xefd5102a, 0x71b18589, 0x06b6b51f, 0x9fbfe4a5, 0xe8b8d433, 0x7807c9a2, 0x0f00f934,
            0x9609a88e, 0xe10e9818, 0x7f6a0dbb, 0x086d3d2d, 0x91646c97, 0xe6635c01, 0x6b6b51f4,
            0x1c6c6162, 0x856530d8, 0xf262004e, 0x6c0695ed, 0x1b01a57b, 0x8208f4c1, 0xf50fc457,
            0x65b0d9c6, 0x12b7e950, 0x8bbeb8ea, 0xfcb9887c, 0x62dd1ddf, 0x15da2d49, 0x8cd37cf3,
            0xfbd44c65, 0x4db26158, 0x3ab551ce, 0xa3bc0074, 0xd4bb30e2, 0x4adfa541, 0x3dd895d7,
            0xa4d1c46d, 0xd3d6f4fb, 0x4369e96a, 0x346ed9fc, 0xad678846, 0xda60b8d0, 0x44042d73,
            0x33031de5, 0xaa0a4c5f, 0xdd0d7cc9, 0x5005713c, 0x270241aa, 0xbe0b1010, 0xc90c2086,
            0x5768b525, 0x206f85b3, 0xb966d409, 0xce61e49f, 0x5edef90e, 0x29d9c998, 0xb0d09822,
            0xc7d7a8b4, 0x59b33d17, 0x2eb40d81, 0xb7bd5c3b, 0xc0ba6cad, 0xedb88320, 0x9abfb3b6,
            0x03b6e20c, 0x74b1d29a, 0xead54739, 0x9dd277af, 0x04db2615, 0x73dc1683, 0xe3630b12,
            0x94643b84, 0x0d6d6a3e, 0x7a6a5aa8, 0xe40ecf0b, 0x9309ff9d, 0x0a00ae27, 0x7d079eb1,
            0xf00f9344, 0x8708a3d2, 0x1e01f268, 0x6906c2fe, 0xf762575d, 0x806567cb, 0x196c3671,
            0x6e6b06e7, 0xfed41b76, 0x89d32be0, 0x10da7a5a, 0x67dd4acc, 0xf9b9df6f, 0x8ebeeff9,
            0x17b7be43, 0x60b08ed5, 0xd6d6a3e8, 0xa1d1937e, 0x38d8c2c4, 0x4fdff252, 0xd1bb67f1,
            0xa6bc5767, 0x3fb506dd, 0x48b2364b, 0xd80d2bda, 0xaf0a1b4c, 0x36034af6, 0x41047a60,
            0xdf60efc3, 0xa867df55, 0x316e8eef, 0x4669be79, 0xcb61b38c, 0xbc66831a, 0x256fd2a0,
            0x5268e236, 0xcc0c7795, 0xbb0b4703, 0x220216b9, 0x5505262f, 0xc5ba3bbe, 0xb2bd0b28,
            0x2bb45a92, 0x5cb36a04, 0xc2d7ffa7, 0xb5d0cf31, 0x2cd99e8b, 0x5bdeae1d, 0x9b64c2b0,
            0xec63f226, 0x756aa39c, 0x026d930a, 0x9c0906a9, 0xeb0e363f, 0x72076785, 0x05005713,
            0x95bf4a82, 0xe2b87a14, 0x7bb12bae, 0x0cb61b38, 0x92d28e9b, 0xe5d5be0d, 0x7cdcefb7,
            0x0bdbdf21, 0x86d3d2d4, 0xf1d4e242, 0x68ddb3f8, 0x1fda836e, 0x81be16cd, 0xf6b9265b,
            0x6fb077e1, 0x18b74777, 0x88085ae6, 0xff0f6a70, 0x66063bca, 0x11010b5c, 0x8f659eff,
            0xf862ae69, 0x616bffd3, 0x166ccf45, 0xa00ae278, 0xd70dd2ee, 0x4e048354, 0x3903b3c2,
            0xa7672661, 0xd06016f7, 0x4969474d, 0x3e6e77db, 0xaed16a4a, 0xd9d65adc, 0x40df0b66,
            0x37d83bf0, 0xa9bcae53, 0xdebb9ec5, 0x47b2cf7f, 0x30b5ffe9, 0xbdbdf21c, 0xcabac28a,
            0x53b39330, 0x24b4a3a6, 0xbad03605, 0xcdd70693, 0x54de5729, 0x23d967bf, 0xb3667a2e,
            0xc4614ab8, 0x5d681b02, 0x2a6f2b94, 0xb40bbe37, 0xc30c8ea1, 0x5a05df1b, 0x2d02ef8d };

    public static int crc32Hash(String key) {
        int n = key.length();
        int hash = n;
        for (int i = 0; i < n; ++i) {
            hash = (hash >> 8) ^ CRCTAB[(hash & 0xff) ^ key.charAt(i)];
        }
        return hash;
    }

    public static int additiveHash(String key) {
        int n = key.length();
        int hash = n;
        for (int i = 0; i < n; i++) {
            hash += key.charAt(i);
        }
        return hash ^ (hash >> 10) ^ (hash >> 20);
    }

    public static int rotatingHash(String key) {
        int n = key.length();
        int hash = n;
        for (int i = 0; i < n; i++) {
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * @param key
     * @param prime
     *            31 131 1313 13131 131313 etc..
     * @return
     */
    public static int bkdrHash(String key, int prime) {
        int hash = 0;
        int n = key.length();
        for (int i = 0; i < n; ++i) {
            hash = prime * hash + key.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    public static int fnvHash(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return (hash & 0x7FFFFFFF);
    }

    public static int rsHash(String key) {
        int b = 378551;
        int a = 63689;
        int hash = 0;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            hash = hash * a + key.charAt(i);
            a = a * b;
        }
        return (hash & 0x7FFFFFFF);
    }

    public static int jsHash(String key) {
        int hash = 1315423911;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            hash ^= ((hash << 5) + key.charAt(i) + (hash >> 2));
        }
        return (hash & 0x7FFFFFFF);
    }

    // P. J. Weinberger Hash Function
    public static int pjwHash(String key) {
        int bitsInUnignedInt = 32;
        int threeQuarters = 24;
        int oneEighth = 4;
        int highBits = (0xFFFFFFFF) << (bitsInUnignedInt - oneEighth);
        int hash = 0;
        int test = 0;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            hash = (hash << oneEighth) + key.charAt(i);
            if ((test = hash & highBits) != 0) {
                hash = ((hash ^ (test >> threeQuarters)) & (~highBits));
            }
        }
        return (hash & 0x7FFFFFFF);
    }

    public static int elFhash(String key) {
        int h = 0;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            h = (h << 4) + key.charAt(i);
            long g = h & 0Xf0000000L;
            if (g != 0) {
                h ^= g >> 24;
                h &= ~g;
            }
        }
        return (h & 0x7FFFFFFF);
    }

    // SDBM Hash Function
    public static int sdbmHash(String key) {
        int hash = 0;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            hash = key.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }
        return (hash & 0x7FFFFFFF);
    }

    // DJB Hash Function
    public static int djbHash(String key) {
        int hash = 5381;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            hash += (hash << 5) + key.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    // AP Hash Function
    public static int apHash(String key) {
        int hash = 0;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                hash ^= ((hash << 7) ^ key.charAt(i) ^ (hash >> 3));
            } else {
                hash ^= (~((hash << 11) ^ key.charAt(i) ^ (hash >> 5)));
            }
        }
        return (hash & 0x7FFFFFFF);
    }
}
```



### 解法2：Trie树（字典树）

优点：占用内存更小

缺点：时间时间复杂度相对高一些，因为每次加入一个字符串时，都要进行一次树搜索！

```java
/**
 * trie树解决问题
 */
public class Solution2 {

    private TrieNode root = new TrieNode();
    private int maxTime = 0;
    private String maxStr = "";

    /**
     * 新增字符串
     *
     * @param str
     */
    public void addString(String str) {
        TrieNode cur = root;
        for (char c : str.toCharArray()) {
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new TrieNode(c));
            }
            cur = cur.next.get(c);
        }
        cur.add();
        if (maxTime < cur.num) {
            maxStr = str;
            maxTime = cur.num;
        }
    }

    /**
     * 获取出现次数最多的字符串
     *
     * @return
     */
    public String getMaxReferenceStr() {
        return maxStr;
    }

    /**
     * 获取最大出现次数
     *
     * @return
     */
    public int getMaxReferenceTime() {
        return maxTime;
    }

    static class TrieNode {
        char c;
        int num = 0;// 个数统计
        HashMap<Character, TrieNode> next = new HashMap<>();// 子节点树

        public TrieNode() {
        }

        public TrieNode(char c) {
            this.c = c;
        }

        public void add() {
            num++;
        }
    }


    public static void main(String[] args) {
        String[] strings = {"Trie", "insert", "search", "search", "startsWith", "insert", "search"};
        Solution2 solution = new Solution2();
        for (String string : strings) {
            solution.addString(string);
        }
        System.out.println(solution.getMaxReferenceStr());
        System.out.println(solution.getMaxReferenceTime());
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

只遍历一次，但是中间有可能出现内存溢出的情况，当然也可以只选取其中一个出现次数为N的，不要其他出现次数为N的字符串

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
            int hashIndex1 = Math.abs(HashUtils.rsHash(string)) % countMatrixArrangeLen;
            int hashIndex2 = Math.abs(HashUtils.apHash(string)) % countMatrixArrangeLen;
            int hashIndex3 = Math.abs(HashUtils.djbHash(string)) % countMatrixArrangeLen;

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
            int hashIndex1 = Math.abs(HashUtils.rsHash(string)) % countMatrixArrangeLen;
            int hashIndex2 = Math.abs(HashUtils.apHash(string)) % countMatrixArrangeLen;
            int hashIndex3 = Math.abs(HashUtils.djbHash(string)) % countMatrixArrangeLen;

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

