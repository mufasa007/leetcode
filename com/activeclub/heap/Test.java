package com.activeclub.heap;

public class Test {
    public static void main(String[] args) {
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();

        System.out.println("max="+max+"字节,\t"+(max/(double)1024/1024) + "MB");
        System.out.println("total="+total+"字节,\t"+(total/(double)1024/1024) + "MB");
    }
}

/*
max=3743416320字节,	3570.0MB    最大内存的1/4
total=253231104字节,	241.5MB     最大内存的1/64
0.067
1/16=0.0625

-Xms1024m -Xmx1024m -XX:+PrintGCDetails
max=1029177344字节,	981.5MB
max=1029177344字节,	981.5MB
jvm中间商赚差价
Heap
 PSYoungGen      total 305664K, used 26214K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
  eden space 262144K, 10% used [0x00000000eab00000,0x00000000ec499b50,0x00000000fab00000)
  from space 43520K, 0% used [0x00000000fd580000,0x00000000fd580000,0x0000000100000000)
  to   space 43520K, 0% used [0x00000000fab00000,0x00000000fab00000,0x00000000fd580000)
 ParOldGen       total 699392K, used 0K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
  object space 699392K, 0% used [0x00000000c0000000,0x00000000c0000000,0x00000000eab00000)
 Metaspace       used 3169K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 328K, capacity 386K, committed 512K, reserved 1048576K

 */

/*
OOM
1，尝试扩大堆内存看结果
2，分析内存，看一下那个地方出现了问题（专业工具）

-Xms1024m -Xmx1024m -XX:+PrintGCDetails

 */