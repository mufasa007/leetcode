package src.leetcode.com;

import java.util.*;

class Solution204 {

    // 注意题目：是小于n的数
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        } else if (n == 3) {
            return 1;
        }

        List<Integer> list = new ArrayList<>();
        list.add(2);
        int count = 0;

        boolean flag = true;
        for (int i = 2; i < n; i++) {

            // 使用历史质数校验
            for (Integer prime : list) {
                if (i % prime == 0) {
                    flag = false;
                    break;
                }
            }

            // 如果历史质数校验不通过，则直接跳到下一步
            if (!flag) {
                flag = true;
                continue;
            }

            // 如果历史质数校验通过,那么就校验其他数据
            for (int x = list.get(list.size() - 1); x * x < i; x++) {
                if (i % x == 0) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                flag = true;
                continue;
            }
            list.add(i);

        }
        return list.size();
    }

    // 埃氏筛
    public int countPrimes2(int n) {
        boolean[] isPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if(!isPrime[i]){
                count++;
                for(int j= 2*i;j<n;j+=i){
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }

    // 线性埃塞法
    public int countPrimes3(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);

        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            // 二次筛选，如果已经在之前就被筛选过就直接跳出循环
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }


    public static void main(String[] args) {
        Solution204 solution204 = new Solution204();
        System.out.println(solution204.countPrimes(1));
        System.out.println(solution204.countPrimes(2));
        System.out.println(solution204.countPrimes(3));
        System.out.println(solution204.countPrimes(4));
        System.out.println(solution204.countPrimes(499979));
    }
}