package src.enterprise.com.cmb20220327;

import java.util.Scanner;

public class SolutionInterview1 {
    public static void main(String[] args) {
        SolutionInterview1 solution = new SolutionInterview1();
        Scanner sc = new Scanner(System.in);
        while (true){
            if(sc.hasNextInt()){
                System.out.println(deal(sc.nextInt()));
            }
        }
    }

    public static String deal(int n){
        StringBuilder sf = new StringBuilder();
        while (n!=0){
            sf.append((n%2==1?"1":"0"));
            n/=2;
        }
        return sf.toString().equals("") ?"0":sf.reverse().toString();
    }
}
