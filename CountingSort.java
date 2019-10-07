public class CountingSort {
    
    //instance variable used to count the number of times line 17 and 27 is being executed
    private int count;
    
    //empty constructor used to initialize the CountingSort class
    public CountingSort(){
        
    }
    
    //method used to sort array by the given sorting class
    public int[] sortArray(int[] a, int size) {
        int[] b = new int[size];//output (sorted array)
        int maxElem = findMaxElem(a,size);//this determines the value of k to be used for the size of the count array
        int[] c = new int[maxElem+1];
        for(int i = 0 ; i < maxElem; i++){
            c[i] = 0;
        }
        for(int j = 0; j < size; j++){
            c[a[j]]++;
        }
        for(int i = 1; i <= maxElem; i++){
            c[i] += c[i-1];
        }
        for(int i = size-1; i >= 0; i--){
            count++;
            b[c[a[i]]-1] = a[i];
            c[a[i]]--;
        }
        return b;
    }
    
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
