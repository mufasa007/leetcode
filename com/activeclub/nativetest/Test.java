package com.activeclub.nativetest;

public class Test {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread());
        },"A").start();
    }

    // native:凡是带了native关键字的，说明java的作用范围达不到，会去调用底层c语言的库
    // 会进入本地方法栈
    // 会调研本地方法接口，JNI
    // JNI作用：扩展java的使用，融合不同的编程语言为java所用！
    // 最初：C,C++
    // java诞生的时候，C、C++横行，想要立足必须要调用C++
    // 在内存区域专门开辟了一块标记区域：native method stack，登记native方法
    // 最终执行的时候，加载本地方法库中的方法通过JNI执行

    // java程序驱动打印机，管理系统，在企业级应用中
    private native void start0();
}
