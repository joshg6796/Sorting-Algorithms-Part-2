import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Project2Main {
    public static void main (String[] args){
        for(int i  = 0; i < args.length; i++){
            String fname = args[i];
            int size = countSize(fname);
            int[] unsortedArr = readUnsortedArray(fname, size);//unsorted array used for sorting
            CountingSort cs = new CountingSort();
            int[] sortedArr = cs.sortArray(unsortedArr, size);
            int count = cs.getCount();
            writeSortedArray(sortedArr,size,fname,"Counting_Sort", count);
            
            unsortedArr = readUnsortedArray(fname, size);//reassign the unsorted array for the next sort
            RadixSort rs = new RadixSort();
            sortedArr = rs.sortArray(unsortedArr, size);
            count = rs.getCount();
            writeSortedArray(sortedArr,size,fname,"Radix_Sort",count);
            
            unsortedArr = readUnsortedArray(fname, size);//reassign the unsorted array for the next sort
            BucketSort bs = new BucketSort();
            sortedArr = bs.sortArray(unsortedArr, size);
            count =  bs.getCount();
            writeSortedArray(sortedArr,size,fname,"Bucket_Sort",count);
        }
    }
    
    //write sorted array into a new text file
    public static void writeSortedArray(int[] sortedArr, int size, String fname, String sortingMethod, int count) {
        String outputFileName = fname.substring(0, fname.indexOf("."))+sortingMethod+".txt";
        try {
            PrintStream out = new PrintStream(new FileOutputStream(outputFileName));
            System.setOut(out);
            System.out.println(fname + " after " + sortingMethod);
            for(int i = 0; i < size; i++){
                System.out.print(sortedArr[i] + " ");
            }
            System.out.println();
            System.out.println("count = "+ count + "\n");
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            System.out.println("Output for " + fname + " after " + sortingMethod + " is in the file " + outputFileName);
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    //method for printing array
    public static void printArray(int[] arr, int size) {
        for (int i = 0; i < size; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    //counts the number of elements in the file
    public static int countSize(String fname) {
        int size = 0;
        Scanner sc = null;
        String tmps;
        int elem;
        
        try {
            sc = new Scanner(new File(fname));
            while (sc.hasNext()) {
                tmps = sc.next();
                elem = Integer.parseInt(tmps);
                size++;
            }
            sc.close();
            
        } catch (Exception e) {
            e.printStackTrace();;
        }
        
        return size;
    }
    
    //puts all the elements in the text file into an array
    public static int[] readUnsortedArray(String fname, int size) {
        // TODO Auto-generated method stub
        int[] arr = new int[size];
        Scanner sc = null;
        String tmps;
        int elem;
        
        try {
            sc = new Scanner(new File(fname));
            int idx = 0;
            while (sc.hasNext()) {
                tmps = sc.next();
                elem = Integer.parseInt(tmps);
                arr[idx] = elem;
                idx++;
            }
            sc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return arr;
    }
}
