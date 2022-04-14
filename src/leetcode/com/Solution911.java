package src.leetcode.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution911 {


    public static void main(String[] args) {
        Solution911 solution = new Solution911();
        int[] persons ={0,1,1,0,0,1,0};
        int[] times ={0,5,10,15,20,25,30};

        TopVotedCandidate2 candidate = new TopVotedCandidate2(persons, times);
        System.out.println(candidate.q(3));
        System.out.println(candidate.q(12));
        System.out.println(candidate.q(25));
        System.out.println(candidate.q(15));
        System.out.println(candidate.q(24));
        System.out.println(candidate.q(8));

        System.out.println();
    }
}

// 解法二
// 直接进行统计以一个max值作为标识，无需使用链表
class TopVotedCandidate2 {
    // 意义:用于存放特定候选人信息当前票数信息
    private HashMap<Integer, PersonInfo> hashMap;
    // 应该存储一个节点 value-> id 和 times 时间节点
    private List<PersonTime> firstPerson = new ArrayList<>();
    int max = Integer.MIN_VALUE;

    public TopVotedCandidate2(int[] persons, int[] times) {
        hashMap = new HashMap<>();
        int len = times.length;
        PersonInfo personInfo = null;
        for (int i = 0; i < len; i++) {
            if (hashMap.containsKey(persons[i])) {
                // 针对历史存在的候选人新增
                personInfo = hashMap.get(persons[i]);
                personInfo.value++;
            } else {
                // 新增候选人
                personInfo = new PersonInfo(persons[i], 1);
                hashMap.put(persons[i], personInfo);
            }
            if(personInfo.value>=max){
                max = personInfo.value;
                firstPerson.add(new PersonTime(personInfo.id,times[i]));
            }
        }
    }

    // 查询t时刻的优胜候选人id
    public int q(int t) {
        if(t<firstPerson.get(0).time){
            // 情况1：还未开始投票
            return -1;
        }

        if(t>=firstPerson.get(firstPerson.size()-1).time){
            // 情况2：最后一次投票之后
            return firstPerson.get(firstPerson.size()-1).id;
        }

        int l = 0,r = firstPerson.size()-1;
        int index =l + ((r-l)>>1);
        while (l<r && l!=index){
            // 使用二分查找法，寻找目标时间区间
            if(t<firstPerson.get(index).time){
                r=index;
            }else if(t==firstPerson.get(index).time){
                return firstPerson.get(index).id;
            }else {
                l = index;
            }
            index= l + ((r-l)>>1);
        }
        return firstPerson.get(index).id;
    }

    // 候选人逻辑，不记录时间
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
    }

    // 专门用于记录某一个时刻的领先选举人变更
    static class PersonTime {
        int id;
        int time;

        public PersonTime(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }
}

/*
 * 解题思路：
 * 1，直接使用选举人变更的开始时间进行记录
 * */
class TopVotedCandidate {

    // 头节点：用于获取得票数最高的人
    private PersonInfo head = new PersonInfo(-1, Integer.MAX_VALUE);
    // 尾结点：为了新增成员
    private PersonInfo tail = new PersonInfo(-1, Integer.MAX_VALUE);

    // 应该存储一个节点 value-> id 和 times 时间节点
    private List<PersonTime> firstPerson = new ArrayList<>();
    // 意义:用于存放特定候选人信息当前票数信息
    private HashMap<Integer, PersonInfo> hashMap;

    public TopVotedCandidate(int[] persons, int[] times) {
        hashMap = new HashMap<>();
        head.next = tail;
        tail.pre = head;

        int len = times.length;
        for (int i = 0; i < len; i++) {
            if (hashMap.containsKey(persons[i])) {
                // 针对历史存在的候选人新增
                PersonInfo personInfo = hashMap.get(persons[i]);
                personInfo.incr();
            } else {
                // 新增候选人
                PersonInfo personInfo = new PersonInfo(persons[i], 1);
                addNode(personInfo);
                hashMap.put(persons[i], personInfo);
            }
            checkPerson(firstPerson,head.next,times[i]);
        }
    }

    // 查询t时刻的优胜候选人id
    public int q(int t) {
        if(t<firstPerson.get(0).time){
            // 情况1：还未开始投票
            return -1;
        }

        if(t>=firstPerson.get(firstPerson.size()-1).time){
            // 情况2：最后一次投票之后
            return firstPerson.get(firstPerson.size()-1).id;
        }

        int l = 0,r = firstPerson.size()-1;
        int index =l + ((r-l)>>1);
        while (l<r && l!=index){
            // 使用二分查找法，寻找目标时间区间
            if(t<firstPerson.get(index).time){
                r=index;
            }else if(t==firstPerson.get(index).time){
                return firstPerson.get(index).id;
            }else {
                l = index;
            }
            index= l + ((r-l)>>1);
        }
        return firstPerson.get(index).id;
    }

    // 新增选举人（新增完也需要排序）
    public void addNode(PersonInfo personInfo) {
        PersonInfo curPre = tail.pre;
        curPre.next = personInfo;
        personInfo.pre = curPre;

        tail.pre = personInfo;
        personInfo.next = tail;
        personInfo.sortFromPre();
    }

    // 用于检测结果人员变更情况
    public void checkPerson(List<PersonTime> personTimeList, PersonInfo first, int time) {
        if (personTimeList.size() <= 0 || personTimeList.get(personTimeList.size() - 1).id != first.id) {
            personTimeList.add(new PersonTime(first.id, time));
        }
    }

    // 专门用于记录某一个时刻的领先选举人变更
    static class PersonTime {
        int id;
        int time;

        public PersonTime(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    // 候选人逻辑，不记录时间
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

        // 新增票数
        public void incr() {
            value++;
            sortFromPre();
        }

        // 以该节点为起点，向前排序
        private void sortFromPre() {
            if (pre.value <= 0) {
                return;
            }

            // 直接开始冒泡排序（小于等于都变更->使用最新的人）
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
