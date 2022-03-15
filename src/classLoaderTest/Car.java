package src.classLoaderTest;

public class Car {

    public static void main(String[] args) {

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
        System.out.println(car3.hashCode());

        Class<? extends Car> aClass1 = car1.getClass();
        Class<? extends Car> aClass2 = car2.getClass();
        Class<? extends Car> aClass3 = car3.getClass();

        System.out.println(aClass1.hashCode());
        System.out.println(aClass2.hashCode());
        System.out.println(aClass3.hashCode());

        ClassLoader classLoader = aClass1.getClassLoader();
        System.out.println(classLoader);// AppClassLoader 应用程序类加载器
        System.out.println(classLoader.getParent()); // 扩展类加载器 jre\lib\ext
        System.out.println(classLoader.getParent().getParent()); // 不存在

    }



}

/*
输出
1808253012
589431969
1252169911
1975012498
1975012498
1975012498
sun.misc.Launcher$AppClassLoader@18b4aac2
sun.misc.Launcher$ExtClassLoader@7d4991ad
null
*/

/*
jre/lib/rt.jar
jre/lib/ext

rt和ext是可以进行调优的，修改里面的包替换为自己的

 */