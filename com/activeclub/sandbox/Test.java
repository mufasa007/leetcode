package com.activeclub.sandbox;

public class Test {

    static long count= 0L;
    public void a(){
        System.out.println(count++);
        b();
    }

    public  void b(){
        System.out.println(count++);
        a();
    }


    public static void main(String[] args) {
        new Test().a();
    }
}

/*
10501
Exception in thread "main" java.lang.StackOverflowError
	at com.activeclub.sandbox.Test.b(Test.java:9)
	at com.activeclub.sandbox.Test.a(Test.java:5)
 */