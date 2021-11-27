import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class function1 {

    public static void heapSort(Integer[] A) {

        //reference the pseudocode in [CLRS] Chapter6
        buildHeap(A);

        for (int i = A.length-1; i >= 1; i--){
            swap(A,0,i);
            heapify(A, i,0);
        }
    }

    //build a max heap
    public static void buildHeap(Integer[] A) {
        int idx = A.length/2;
        for (int i = idx; i >= 0; i--){
            heapify(A, A.length, i);
        }
    }

    //adjust the remaining unsorted deadlines into a new max-heap
    public static void heapify(Integer[] A, int size, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int largest;
        if (left < size && A[left] > A[index]) {
            largest = left;
        }
        else{
            largest = index;
        }

        if (right < size && A[right] > A[largest]) {
            largest = right;
        }

        if (index != largest) {
            swap(A, index,largest);

            heapify(A, size, largest);
        }
    }

    //swap the first element and the last element in the heap of deadlines
    public static void swap(Integer[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }



    public static void main(String[] args) throws IOException {
        //read the test file and store the data into ArrayList
        File file = new File("/Users/caitong/Documents/2020S2/COMP3600/assignment/final project/final-u6619441/Program-u6619441/test/test-f1-1.txt");
        Scanner scanner = new Scanner(file);
        List<Integer> list = new ArrayList<Integer>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
            else {
                scanner.next();
            }
        }

        long starTime = System.currentTimeMillis();
        //transfer ArrayList<Integer> to Integer[]
        Integer[] A = list.toArray(new Integer[list.size()]);

        System.out.println("Before Heapsort: " + Arrays.toString(A));

        heapSort(A);

        System.out.println("After Heapsort: " + Arrays.toString(A));

        System.out.println("Input Size: " + A.length);

        long endTime = System.currentTimeMillis();
        System.out.println("Total Time："+(endTime - starTime)+"ms");
    }

}