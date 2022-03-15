package com.activeclub.outOfMemory;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        String hello = new String("hello");

        while (true){
            hello += hello + new Random().nextInt(888888888)+
                    new  Random().nextInt(888888888);
        }
    }
}

/*
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3332)
	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:448)
	at java.lang.StringBuilder.append(StringBuilder.java:141)
	at com.activeclub.outOfMemory.Test.main(Test.java:11)



-Xms8m -Xmx8m -XX:+PrintGCDetails
-Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError

 */