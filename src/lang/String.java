//package java.lang;
//
///*
//双亲委派机制：安全
//1，APP->EXC->BOOT（最终执行）
//2，然后从 BOOT->EXC-APP 一路往下找，一旦找到就直接使用
// */
//
//public class String {
//    public String toString(){
//        return "wanyu";
//    }
//
//    public static void main(String[] args) {
//        String s = new String();
//        System.out.println(s.toString());
//    }
//}
//
///*
//错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
//   public static void main(String[] args)
//否则 JavaFX 应用程序类必须扩展javafx.application.Application
// */