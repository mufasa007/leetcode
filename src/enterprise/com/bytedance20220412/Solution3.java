package src.enterprise.com.bytedance20220412;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();

        int[] persons = {1, 3, 1, 3, 3, 1, 3};
        int[] times = {0, 5, 10, 15, 20, 25, 30};

        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);

        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNext()) {
                int q = topVotedCandidate.q(sc.nextInt());
                System.out.println(q);
            }
        }
    }
}

// 优化为区间形式
class TopVotedCandidate {

    // 利用treeMap的排序性进行重置
    private PersonInfo head = new PersonInfo(-1, Integer.MAX_VALUE);
    private PersonInfo tail = new PersonInfo(-1, Integer.MIN_VALUE);
    private int originFristOne = -1;

    private List<TimeRange> firstOne = new ArrayList<>();
    private Integer len = 0;
    //    private List<Integer> firstPerson = new ArrayList<>();
    private HashMap<Integer, PersonInfo> hashMap;

    public TopVotedCandidate(int[] persons, int[] times) {
        hashMap = new HashMap<>();
        head.next = tail;
        tail.pre = head;

        int len = times.length;
        int curTime = 0;
        PersonInfo personInfo;
        for (int i = 0; i < len; i++) {
            if (hashMap.containsKey(persons[i])) {
                personInfo = hashMap.get(persons[i]);
                personInfo.incr();
            } else {
                personInfo = new PersonInfo(persons[i], 1);
                addNode(personInfo);
                hashMap.put(persons[i], personInfo);
            }
            checkFirstOne(personInfo, times[i]);
        }
    }

    public int q(int t) {
        if (t >= firstOne.get(len - 1).time) {
            return firstOne.get(len - 1).id;
        } else if (t < firstOne.get(0).time) {
            return -1;
        }

        // 二分查找时间区间
        int l = 0, r = len - 1, mide;
        TimeRange timeRange = null;
        while (l < r) {
            mide = l + ((r - l) >> 1);
            timeRange = firstOne.get(mide);
            if (t < timeRange.time) {
                r = mide - 1;
            } else if (timeRange.time < t) {
                l = mide + 1;
            } else {
                return timeRange.id;
            }
        }
        return timeRange.id;
    }

    private void checkFirstOne(PersonInfo personInfo, Integer time) {
        if (originFristOne != head.next.id) {
            originFristOne = head.next.id;
            firstOne.add(new TimeRange(originFristOne, time));
            len++;
        } else if (len > 0) {
            TimeRange cur = firstOne.get(len - 1);
            cur.time = time;
        }
    }

    private void addNode(PersonInfo personInfo) {
        PersonInfo curPre = tail.pre;
        curPre.next = personInfo;
        personInfo.pre = curPre;

        tail.pre = personInfo;
        personInfo.next = tail;
        personInfo.sortFromPre();
    }

    static class TimeRange {
        int id;
        int time;

        public TimeRange(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    static class PersonInfo {
        int id;
        int value;

        PersonInfo pre;
        PersonInfo next;

        public PersonInfo() {
        }

        public PersonInfo(int id, int value) {
            this.id = id;
            this.value = value;
        }

        public void incr() {
            value++;
            sortFromPre();
        }

        private void sortFromPre() {
            if (pre.value == 0) {
                return;
            }

            // 直接开始冒泡排序
            // 1-2-【3】-4-5
            // 1-2-4-5
            // 1-【3】-2-4-5
            while (pre.value <= value) {
                PersonInfo curPrePre = pre.pre;
                PersonInfo curPre = pre;

                // 前后节点变更
                if (next != null) {
                    next.pre = pre;
                }
                pre.next = next;

                // 前前节点变更
                curPrePre.next = this;
                this.pre = curPrePre;

                // 前节点变更
                curPre.pre = this;
                this.next = curPre;
            }
        }
    }

}