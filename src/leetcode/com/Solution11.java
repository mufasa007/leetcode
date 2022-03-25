package src.leetcode.com;

/**
 * @author zjk
 * @date 2022/3/25
 * @descript
 * @since V1.0.0
 */
public class Solution11 {
  public static void main(String[] args) {
    int[] ints = {1,8,6,2,5,4,8,3,7};
    int size = maxArea(ints);
    System.out.println(size);
  }

  public static int maxArea(int[] height) {
    int max = 0,len = height.length;
    int l=0,r=len-1;
    while (l < r) {
      max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
      if(height[l]<height[r]){
        l++;
      }else if(height[l]>height[r]){
        r--;
      }else {
        if(height[l+1]>height[r-1]){
          l++;
        }else {
          r--;
        }
      }
    }
    return max;
  }
}
