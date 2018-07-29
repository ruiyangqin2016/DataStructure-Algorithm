import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Ruiyang Qin
 * @userid rqin37
 * @GTID 903146737
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "the array or comparator is null");
        }

        int length = arr.length;
        int i = 0;
        boolean swapped = true;
        while (i < length - 1 && swapped) {
            swapped = false;
            for (int j = 0; j < length - 1 - i; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            i++;
        }
    }


    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "the array or comparator is null");
        }

        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(arr, j - 1, j);
                j--;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "the array or comparator is null");
        }

        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algoarithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException(
                    "the array or comparator or random is null");
        }

        quickSortHelper(arr, 0, arr.length - 1, comparator, rand);
    }

    /**
     *
     * @param arr the array that must be sorted after the method runs
     * @param posLeft left index after pivot
     * @param posRight right index after pivot
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param <T> data type to sort
     */
    private static <T> void quickSortHelper(T[] arr, int posLeft, int
            posRight, Comparator<T> comparator, Random rand) {

        if (posRight - posLeft == 1) { return; } //when the array is 1
        if (posRight - posLeft <= 0) { return; } //when the array is empty

        int pivotIndex = rand.nextInt(posRight - posLeft) + posLeft;
        T pivot = arr[pivotIndex];
        swap(arr, posLeft, pivotIndex);
        int l = posLeft + 1;
        int r = posRight;

        while (l <= r) {
            while (l <= r && comparator.compare(arr[l], pivot) <= 0) { l++; }
            while (l <= r && comparator.compare(arr[r], pivot) >= 0) { r--; }
            if (l <= r) {
                swap(arr, l, r);
                l++;
                r--;
            }
        }
        swap(arr, posLeft, r);
        quickSortHelper(arr, posLeft, r, comparator, rand);
        quickSortHelper(arr, r + 1, posRight, comparator, rand);
    }


    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "the array or comparator is null");
        }
        int length = arr.length;
        if (length <= 1) { return; }
        int midIndex = length / 2;

        T[] leftArr = (T[]) new Object[midIndex];
        for (int i = 0; i < midIndex; i++) { leftArr[i] = arr[i]; }

        T[] rightArr = (T[]) new Object[length - midIndex];
        int j = 0;
        for (int i = midIndex; i < length; i++) {
            rightArr[j] = arr[i];
            j++;
        }

        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);

        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;

        /*
        execute only if both side is not empty
         */
        while (leftIndex < midIndex && rightIndex < length - midIndex) {
            if (comparator.compare(leftArr[leftIndex], rightArr[rightIndex])
                    <= 0) {
                arr[currentIndex] = leftArr[leftIndex];
                leftIndex++;
            } else {
                arr[currentIndex] = rightArr[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        /*
        empty out everything in the rest of array
         */
        while (leftIndex < midIndex) {
            arr[currentIndex] = leftArr[leftIndex];
            leftIndex++;
            currentIndex++;
        }

        while (rightIndex < length - midIndex) {
            arr[currentIndex] = rightArr[rightIndex];
            rightIndex++;
            currentIndex++;
        }
    }



    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("the array is null");
        }

        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<Integer>();
        }

        int count = 1;
        if (arr.length == 0) {
            return;
        }
        int longthLongest = iteration(arr);
        while (longthLongest > 10) {
            longthLongest = longthLongest / 10;
            count++;
        }
        int pow = 1;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < arr.length; j++) {
                buckets[(arr[j] / pow) % 10 + 9].add(arr[j]);
            }
            pow = pow * 10;
            int index = 0;
            for (int j = 0; j < 19; j++) {
                while (!buckets[j].isEmpty()) {
                    arr[index] = buckets[j].remove();
                    index++;
                }
            }
        }

    }

    /**
     *
     * @param arr the array to be sorted
     * @return return the longest element
     */
    private static int iteration(int[] arr) {
        int largest = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= largest) {
                largest = arr[i];
            }
        }
        return largest;
    }

    /**
     *
     * @param arr the array that must be sorted after the method runs
     * @param a index of the element
     * @param b index of the element
     * @param <T> data type to sort
     */
    private static <T> void swap(T[] arr, int a, int b) {
        T holdData = arr[a];
        arr[a] = arr[b];
        arr[b] = holdData;
    }
}
