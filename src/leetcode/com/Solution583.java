package src.leetcode.com;

import java.util.*;

public class Solution583 {

    /*
     * 1,先根据到达时间排序
     * 2,查询后到达的车辆有没有①起始点在前面的或者
     *   如果有，这个车辆自动归入，被检测出来的车
     *   如果没有，说明这辆车是独自行驶过弯
     * 3,检测剩余车辆中还有没有同时到达的车辆
     * */

    // 车队数量本质就是时间和起始位置的双重检测
    public int carFleet(int target, int[] position, int[] speed) {
        int len = speed.length;
        float[] time = new float[len];
        Map<Integer, CarInfo> map = new HashMap<>();
        List<CarInfo> carInfoList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            time[i] = ((float) (target - position[i])) / ((float) speed[i]);
            CarInfo carInfo = new CarInfo(i, position[i], time[i], speed[i]);
            map.put(i, carInfo);
            carInfoList.add(carInfo);
        }

        // 根据到达时间排序
        carInfoList.sort(new Comparator<CarInfo>() {
            @Override
            public int compare(CarInfo o1, CarInfo o2) {
                return (o1.time > o2.time ? 1 : -1);
            }
        });

        // 双重检测
        // ①检测起始点
        for (int i = 0; i < len; i++) {
            CarInfo carInfo = carInfoList.get(i);
            boolean flag = true;
            for (int j = i + 1; j < len; j++) {
                boolean isHead = carInfoList.get(j).start >= carInfo.start;
                if (isHead) {
                    map.remove(carInfo.index);
                    break;
                }
            }
        }

        // ②检测剩余车辆到达时间是否有相同的
        int curIndex = 0;
        int nextIndex = 1;
        while (curIndex < nextIndex && nextIndex < len) {
            if (!map.containsKey(curIndex)) {
                curIndex++;
                nextIndex = curIndex + 1;
                continue;
            }
            if (!map.containsKey(nextIndex)) {
                nextIndex++;
                continue;
            }

            if (carInfoList.get(curIndex).time == carInfoList.get(nextIndex).time) {
                map.remove(curIndex);
            }
            curIndex = nextIndex;
            nextIndex++;
        }
        return map.size();
    }

    static class CarInfo {
        int index;
        int start;
        float time;
        int speed;

        public CarInfo(int index, int start, float time, int speed) {
            this.index = index;
            this.start = start;
            this.time = time;
            this.speed = speed;
        }
    }

    public static void main(String[] args) {
        Solution583 solution = new Solution583();
//        int target = 20;
//        int[] position = {2, 6, 5, 13, 19, 18, 1, 12, 10, 16, 4, 11};
//        int[] speed = {6, 1, 10, 3, 1, 5, 9, 7, 9, 2, 8, 3};

        int target = 12;
        int[] position = {10,8,0,5,3};
        int[] speed = {2,4,1,1,3};

        System.out.println(solution.carFleet(target, position, speed));
        System.out.println();
    }
}
