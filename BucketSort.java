import java.util.ArrayList;

public class BucketSort {
    
    //instance variable used to count the number of times line 35 and 73 is being executed
    private int count;
    
    //empty constructor used to initialize the BucketSort class
    public BucketSort(){
        
    }
    
    //method used to sort array by the given sorting class
    public int[] sortArray(int[] arr, int size) {
        float[] floatArr = convertToFloat(arr, size);
        floatArr = generateRVNumbers(floatArr,size);
        ArrayList<ArrayList<Float>> buckets = new ArrayList<>();
        
        //initialize array list in each bucket
        for(int i=0; i < size; i++){
            buckets.add(new ArrayList<Float>());
        }
        
        //add the element to the appropriate bucket
        for(int i = 0; i < size; i++){
            int idx;
            if (floatArr[i] == 0.0) idx = 0;
            else idx = (int)(size*floatArr[i]+1);
            buckets.get(idx).add(floatArr[i]);
        }
        
        int ptr = 0;//pointer used to merge the buckets into one array
        //sort each bucket using insertion sort and then merge the buckets into one array
        for(int i = 0; i < size; i++){
            count++;
            ArrayList<Float> temp = buckets.get(i);
            temp = insertionSort(buckets.get(i));
            for(int j = 0; j < buckets.get(i).size(); j++){
                floatArr[ptr] = buckets.get(i).get(j);
                ptr++;
            }
        }
        
        floatArr = retrieveOriginalNumbers(floatArr,size);
        arr = convertToInt(floatArr, size);
        return arr;
    }
    
    //converts int[] array into a float[] array
    public float[] convertToFloat(int[] arr, int size) {
        float[] floatArr = new float[size];
        for(int i = 0; i < size; i++){
            floatArr[i] = (float)arr[i];
        }
        return floatArr;
    }
    
    //converts float[] array into an int[] array
    public int[] convertToInt(float[] floatArr, int size) {
        int[] intArr = new int[size];
        for(int i = 0; i < size; i++){
            intArr[i] = (int)floatArr[i];
        }
        return intArr;
    }
    
    //modified insertion sort algorithm for floating point numbers implemented using an ArrayList data structure
    public ArrayList<Float> insertionSort(ArrayList<Float> arr) {
        for (int j = 1; j < arr.size(); j++){
            float key = arr.get(j);
            int i = j-1;
            while(i > -1 && arr.get(i) > key){
                count++;
                arr.set(i+1,arr.get(i));
                i--;
            }
            arr.set(i+1,key);
        }
        return arr;
    }
    
    //converts input into real valued numbers in the interval [0,1)
    public float[] generateRVNumbers(float[] floatArr, int size) {
        float minElem = findMinElem(floatArr,size);
        float maxElem = findMaxElem(floatArr,size);
        float x = -minElem;
        for(int i = 0; i < size; i++){
            floatArr[i] = floatArr[i] + x;
        }
        float y = maxElem + 1;
        for(int i = 0; i < size; i++){
            floatArr[i] = floatArr[i] / y;
        }
        return floatArr;
    }
    
    //this method takes the real valued numbers and converts them back to their original numbers
    public float[] retrieveOriginalNumbers(float[] floatArr, int size) {
        float y = size + 1;
        for(int i = 0; i < size; i++){
            floatArr[i] = (floatArr[i] * y) + 1;
        }
        return floatArr;
    }
    
    //finds max element in float array
    public float findMaxElem(float[] floatArr, int size) {
        float max = floatArr[0];
        for (int i = 0; i < size; i++){
            if(floatArr[i] > max)
                max = floatArr[i];
        }
        return max;
    }
    
    //finds min element in float array
    public float findMinElem(float[] floatArr, int size) {
        float min = floatArr[0];
        for (int i = 0; i < size; i++){
            if(floatArr[i] < min)
                min = floatArr[i];
        }
        return min;
    }
    
    //getter function for count
    public int getCount(){
        return count;
    }
    
}
