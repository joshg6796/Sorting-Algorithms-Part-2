public class RadixSort {
    
    //instance variable used to count the number of times line 28 and 38 is being executed
    private int count;
    
    //empty constructor used to initialize the RadixSort class
    public RadixSort(){
        
    }
    
    //method used to sort array by the given sorting class
    public int[] sortArray(int[] arr, int size) {
        int maxElem = findMaxElem(arr, size);//determines the maximum number of digits
        int[] sortedArr = arr;
        //For loop depends on the number with the maximum number of digits. (Ex: if the number is 136, that has 3 digits so the loop will run 3 times)
        for (int place = 1; maxElem/place > 0; place *= 10){
            sortedArr = stableSort(sortedArr, size , place);//perform a stable sort on each digit, starting from the one's place
        }
        return sortedArr;
    }
    
    //Modified version of counting sort that works by digit representation
    public int[] stableSort(int[] a, int size, int place) {
        int maxElem = findMaxElem(a, size);
        int[] b = new int[size];//output (sorted array)
        int[] c = new int[maxElem+1];
        for(int i = 0 ; i < maxElem; i++){
            c[i] = 0;
        }
        for(int j = 0; j < size; j++){
            c[(a[j]/place)%10]++;
        }
        for(int i = 1; i <= maxElem; i++){
            c[i] += c[i-1];
        }
        for(int i = size-1; i >= 0; i--){
            count++;
            b[c[(a[i]/place)%10]-1] = a[i];
            c[(a[i]/place)%10]--;
        }
        return b;
    }
    
    //finds max element in array
    public int findMaxElem(int[] a, int size) {
        int max = a[0];
        for (int i = 0; i < size; i++){
            if(a[i] > max)
                max = a[i];
        }
        return max;
    }
    
    //getter function for count
    public int getCount(){
        return count;
    }
    
}
