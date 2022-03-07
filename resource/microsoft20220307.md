# microsoft算法20220307



题目1

有Str = "ayxbx"，有如下4种切分方法：a|yxb,  ay|xbx,  ayx|bx,  ayxb|x

其中第1、3、4种切法符合：x和y的个数，至少在左右两边中的一块里有相同的数量，所以返回3

给定一个字符串str，长度为N，你有N-1种切法，有多少种切法满足要求

我的解题思路：

直接使用两个List<Integer> indexX, indexY，然后遍历一遍并且计算X，Y的总数

```java
    public static int solution(String str){
        if(str == null || str.length()==0){
            return 0;
        }

        // 统计个数
        List<Integer> indexX = new ArrayList<>();
        List<Integer> indexY = new ArrayList<>();
        int sumX = 0, sumY = 0;

        // 遍历str
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'x') {
                sumX++;
                indexX.add(i);
            } else if (c == 'y') {
                sumY++;
                indexY.add(i);
            }
        }

        // 处理
        if (indexX.size() == 0) {
            // 1,没有x字符
            if (indexY.size() == 0) {
                // 1.1,也没有y字符
                return str.length() - 1;
            }else {
                // 1.2,有y字符
                int start = indexY.get(0);
                int end = indexY.get(indexY.size() - 1);
                return start + (indexY.size() - 1 - end);
            }
        }else {
            // 2,存在x字符
            if(indexY.size() == 0){
                // 2.1,不存在y字符
                int start = indexX.get(0);
                int end = indexX.get(indexX.size() - 1);
                return start + (indexX.size() - 1 - end);
            }else {
                // 2.2,存在y字符
                int maxIndex = indexX.size()>indexY.size() ? indexX.size():indexY.size();
                for (int i = 0; i < maxIndex; i++) {
                    // 计算左右相等。。。
                    
                }
                return 0; // fixme 有点想复杂化了
            }
        }
    }
```

缺点：条件复杂，过程繁琐

优点：几乎没有。。。当时想的是尽可能少的遍历



快捷的解法：

```java
    public static int solution1(String str){
        int sumX=0,sumY=0;
        // 遍历str统计总数
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'x') {
                sumX++;
            } else if (c == 'y') {
                sumY++;
            }
        }

        int preX=0,preY=0,result=0;
        // 遍历第二次进行处理，最后一个字符是不可分的情况
        for (int i = 0; i < str.length()-1; i++) {
            char c = str.charAt(i);
            if (c == 'x') {
                preX++;
            } else if (c == 'y') {
                preY++;
            }
            if(preX == preY || (sumX-preX)==(sumY-preY)){
                result ++;
            }
        }

        return result;
    }
```



