/**
 * @program: data_structure
 * @description: 数组
 * @author: Mr.Tang
 * @create: 2018-07-06 10:03
 **/
public class Array {
    private int[] intArray;

    private int elems;

    private int length;

    public Array(int max) {
        length = max;
        intArray = new int[max];
        elems = 0;
    }

    /**
     * 添加
     * @param value
     */
    public void add(int value){
        if(elems == length){
            System.out.println("error");
            return;
        }
        intArray[elems] = value;
        elems++;
    }


    /**
     * 查找
     * @param searchKey
     * @return
     */
    public int find(int searchKey){
        int i;
        for(i = 0; i < elems; i++){
            if(intArray[i] == searchKey)
                break;
        }
        if(i == elems){
            return -1;
        }
        return i;
    }

    /**
     * 删除
     * @param value
     * @return
     */
    public boolean delete(int value){
        int i = find(value);
        if(i == -1){
            return false;
        }
        for (int j = i; j < elems-1; j++) {
            //后面的数据往前移
            intArray[j] = intArray[j + 1];
        }
        elems--;
        return true;
    }

    /**
     * 更新
     * @param oldValue
     * @param newValue
     * @return
     */
    public boolean update(int oldValue,int newValue){
        int i = find(oldValue);
        if(i == -1){
            return false;
        }
        intArray[i] = newValue;
        return true;
    }

    /**
     * 显示所有
     */
    public void display(){
        for(int i = 0 ; i < elems ; i++){
            System.out.print(intArray[i]+" ");
        }
        System.out.print("\n");
    }


    /**
     * 冒泡排序
     * 每趟冒出一个最大数/最小数
     * 每次运行数量：总数量-运行的趟数(已冒出)
     */
    public void bubbleSort(){
        for(int i = 0; i < elems-1; i++){//排序趟数  n-1次就行了
            System.out.println("第"+(i+1)+"趟：");
            for(int j = 0; j < elems-i-1; j++){//每趟次数 (n-i) -1是防止下标越界，后面赋值用到了+1
                if(intArray[j] > intArray[j+1]){ //控制按大冒泡还是按小冒泡
                    int temp = intArray[j];
                    intArray[j] =  intArray[j+1];
                    intArray[j+1] = temp;
                }
                display();
            }
        }
    }

    /***
     * 选择排序
     * 每趟选择一个最大数/最小数
     * 每次运行数量：总数量-运行的趟数(已选出)
     */
    public void selectSort(){
        for(int i = 0; i < elems-1; i++) {//排序趟数  n-1次就行了
            int min = i;
            for(int j = i+1; j < elems; j++){ //排序次数 每趟选择一个数出来，-1次
                if(intArray[j] < intArray[min]){
                    min = j;
                }
            }
            if(i != min ){
                int temp = intArray[i];
                intArray[i] = intArray[min];
                intArray[min] = temp;
            }
            display();
        }
    }

    /**
     * 插入排序
     * 每趟选择一个待插入的数
     * 每次运行把待插入的数放在比它大/小后面
     */
    public void insertSort(){
        int j;
        for(int i = 1; i < elems; i++){
            int temp = intArray[i];
            j = i;
            while (j > 0 && temp < intArray[j-1]){
                intArray[j] = intArray[j-1];
                j--;
            }
            intArray[j] = temp;
        }
        display();
    }

    public static void main(String[] args) {
        Array array = new Array(10);
        array.add(6);
        array.add(3);
        array.add(8);
        array.add(2);
        array.add(11);
        array.add(5);
        array.add(7);
        array.add(4);
        array.add(9);
        array.add(10);
//        array.bubbleSort();
//        array.selectSort();
        array.insertSort();
//        array.display();
//        System.out.println(array.find(4));
//        System.out.println(array.delete(1));
//        array.display();
//        System.out.println(array.update(2,6));
//        array.display();
    }
}
