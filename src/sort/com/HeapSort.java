package src.sort.com;

// todo 待理解
public class HeapSort extends Sort{
    @Override
    void sort(int[] nums) throws Exception {
        if (nums == null) {
            return;
        }
        int len = nums.length;

        Heap heap = new Heap();
        for (int num : nums) {
            heap.add(num);
        }

        for (int i = 0; i < len; i++) {
            nums[i] = heap.poll();
        }
    }
}

class Heap {
    private static final int CAPACITY=16;
    private static final boolean TYPE=true;

    private static int[] nums;
    private int capacity=16;
    int size=0;

    private boolean type=true;//true由小到大，false由大到小
    public Heap(){
        this(CAPACITY);
    }

    public Heap(int capacity){
        this(capacity,TYPE);
    }

    public Heap(boolean type){
        this(CAPACITY,type);
    }

    public Heap(int capacity,boolean type){
        this.capacity=capacity;
        this.type=type;
        nums=new int[capacity];
    }


    //数据添加
    public void add(int num){
        if(size+1>=capacity){
            dilatate();
        }
        nums[size+1]=num;
        reSortUp(size+1);
        size++;
    }

    private void reSortUp(int index){
        if(type){//由小到大
            while (index!=1){
                if(nums[index/2]>nums[index]){
                    int temp=nums[index];
                    nums[index]=nums[index/2];
                    nums[index/2]=temp;
                    index/=2;
                }else if(nums[index/2]==nums[index]){
//                    throw new IllegalArgumentException("数据结构-堆不接受重复数据输入");
                    break;
                }else {
                    return;
                }
            }
        }else {//由大到小
            while (index!=1){
                if(nums[index/2]<nums[index]){
                    int temp=nums[index];
                    nums[index]=nums[index/2];
                    nums[index/2]=temp;
                    index/=2;
                }else if(nums[index/2]==nums[index]){
//                    throw new IllegalArgumentException("数据结构-堆不接受重复数据输入");
                    break;
                }else {
                    return;
                }
            }
        }
    }

    //数据输出，并且清楚该数据
    public int poll() throws Exception {
        if(size>0){
            int temp=nums[1];
            nums[1]=nums[size];
            reSortDown();
            size--;
            return temp;
        }else {
            throw new Exception("数据为空");
        }
    }

    private void reSortDown(){
        int index=1;
        int L,R;
        if(type){//由小到大
            while (index<size){
                L=index*2;
                R=L+1;
                if(R<=size){
                    boolean flag=nums[L]<nums[R];
                    int min=(flag?nums[L]:nums[R]);
                    if(nums[index]>min){
                        if(flag){
                            int temp=nums[index];
                            nums[index]=nums[L];
                            nums[L]=temp;
                            index=L;
                        }else {
                            int temp=nums[index];
                            nums[index]=nums[R];
                            nums[R]=temp;
                            index=R;
                        }
                    }else {
                        return;
                    }
                }else if(L<=size){
                    if(nums[index]>nums[L]){
                        int temp=nums[index];
                        nums[index]=nums[L];
                        nums[L]=temp;
                    }
                    return;
                }else {
                    return;
                }
            }
        }else {//由大到小
            while (index<size){
                L=index*2;
                R=L+1;
                if(R<size){
                    boolean flag=nums[L]<nums[R];
                    int max=(flag?nums[R]:nums[L]);
                    if(nums[index]<max){
                        if(flag){
                            int temp=nums[index];
                            nums[index]=nums[R];
                            nums[R]=temp;
                            index=R;
                        }else {
                            int temp=nums[index];
                            nums[index]=nums[L];
                            nums[L]=temp;
                            index=L;
                        }
                    }else {
                        return;
                    }
                }else if(L<size){
                    if(nums[index]<nums[L]){
                        int temp=nums[index];
                        nums[index]=nums[L];
                        nums[L]=temp;
                    }
                    return;
                }else {
                    return;
                }
            }
        }
    }

    //数据输出，不清除该数据
    public int peek() throws Exception {
        if(size>0){
            return nums[0];
        }else {
            throw new Exception("数据为空");
        }
    }

    //数据扩容,二倍扩容
    private void dilatate(){
        capacity=capacity<<1;
        int[] pre=new int[capacity];
        for(int i=1;i<=size;i++){
            pre[i]=nums[i];
        }
        nums=pre;
    }

}