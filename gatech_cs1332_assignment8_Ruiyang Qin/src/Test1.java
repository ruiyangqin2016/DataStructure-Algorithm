import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Student test for sorting algorithms.
 *
 * @author Matthew Sklar
 * @version 1.0
 */
public class Test1 {
    private int[] sortInt;
    private int[] sortedInt;
    private int[] reverseInt;
    private int[][] randInt;

    private TeachingAssistant[] sort;
    private TeachingAssistant[] sorted;
    private TeachingAssistant[] reverse;
    private TeachingAssistant[][] rand;

    private ComparatorPlus<TeachingAssistant> comp;

    private static final int LENGTH = 10000;
    private static final int RANDOM_ITERATIONS = 1;
    private static final int TIMEOUT = 10000;

    @Before
    public void setUp() {
        sortInt = new int[LENGTH];
        sortedInt = new int[LENGTH];
        reverseInt = new int[LENGTH];
        randInt = new int[RANDOM_ITERATIONS][LENGTH];

        sort = new TeachingAssistant[LENGTH];
        sorted = new TeachingAssistant[LENGTH];
        reverse = new TeachingAssistant[LENGTH];
        rand = new TeachingAssistant[RANDOM_ITERATIONS][LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            sortInt[i] = i - 5000;
            sortedInt[i] = sortInt[i];
            reverseInt[LENGTH - i - 1] = sortInt[i];

            sort[i] = new TeachingAssistant(i - 5000);
            sorted[i] = sort[i];
            reverse[LENGTH - i - 1] = sort[i];
        }

        for (int i = 0; i < RANDOM_ITERATIONS; i++) {
            System.arraycopy(sort, 0, rand[i], 0, LENGTH);
            Collections.shuffle(Arrays.asList(rand[i]));

            System.arraycopy(sortInt, 0, randInt[i], 0, LENGTH);
            Collections.shuffle(Arrays.asList(randInt[i]));
        }

        comp = TeachingAssistant.getValueComparator();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBubbleExceptionArrayNull() {
        Sorting.bubbleSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBubbleExceptionCompNull() {
        Sorting.bubbleSort(sort, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testInsertionExceptionArrayNull() {
        Sorting.insertionSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testInsertionExceptionCompNull() {
        Sorting.insertionSort(sort, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testSelectionExceptionArrayNull() {
        Sorting.selectionSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testSelectionExceptionCompNull() {
        Sorting.selectionSort(sort, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickSortExceptionArrayNull() {
        Sorting.quickSort(null, comp, new Random());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickSortExceptionCompNull() {
        Sorting.quickSort(sort, null, new Random());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickSortExceptionRandNull() {
        Sorting.quickSort(sort, comp, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeSortExceptionArrayNull() {
        Sorting.mergeSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeSortExceptionCompNull() {
        Sorting.mergeSort(sort, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRadixSortException() {
        Sorting.lsdRadixSort(null);
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSortSorted() {
        Sorting.bubbleSort(sorted, comp);
        assertArrayEquals(sort, sorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                   comp.getCount() == LENGTH - 1);
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSortReverse() {
        Sorting.bubbleSort(reverse, comp);
        assertArrayEquals(sort, reverse);
        assertTrue("Number of comparisons: " + comp.getCount(),
                   comp.getCount() == (LENGTH * (LENGTH - 1)) / 2);
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSortRandom() {
        for (int i = 0; i < RANDOM_ITERATIONS; i++) {
            Sorting.bubbleSort(rand[i], comp);
            assertArrayEquals(sort, rand[i]);
            assertTrue("Number of comparisons: " + comp.getCount(),
                       comp.getCount() >= LENGTH - 1
                       && comp.getCount() <= (LENGTH * (LENGTH - 1)) / 2);

            comp.resetCount();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortSorted() {
        Sorting.insertionSort(sorted, comp);
        assertArrayEquals(sort, sorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                   comp.getCount() == LENGTH - 1);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortReverse() {
        Sorting.insertionSort(reverse, comp);
        assertArrayEquals(sort, reverse);
        assertTrue("Number of comparisons: " + comp.getCount(),
                   comp.getCount() == (LENGTH * (LENGTH - 1)) / 2);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSortRandom() {
        for (int i = 0; i < RANDOM_ITERATIONS; i++) {
            Sorting.insertionSort(rand[i], comp);
            assertArrayEquals(sort, rand[i]);
            assertTrue("Number of comparisons: " + comp.getCount(),
                       comp.getCount() >= LENGTH - 1
                       && comp.getCount() <= (LENGTH * (LENGTH - 1)) / 2);

            comp.resetCount();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortSorted() {
        Sorting.selectionSort(sorted, comp);
        assertArrayEquals(sort, sorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                   comp.getCount() == (LENGTH * (LENGTH - 1)) / 2);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortReverse() {
        Sorting.selectionSort(reverse, comp);
        assertArrayEquals(sort, reverse);
        assertEquals((LENGTH * (LENGTH - 1)) / 2,
                   comp.getCount());
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortRandom() {
        for (int i = 0; i < RANDOM_ITERATIONS; i++) {
            Sorting.selectionSort(rand[i], comp);
            assertArrayEquals(sort, rand[i]);
            assertTrue("Number of comparisons: " + comp.getCount(),
                       comp.getCount() == (LENGTH * (LENGTH - 1)) / 2);

            comp.resetCount();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortSorted() {
        Sorting.quickSort(sorted, comp, new Random());
        assertArrayEquals(sort, sorted);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortReverse() {
        int[] arr = {19, 30, 25, 7, 60, 22, 38, 4};
        Sorting.quickSort(reverse, comp, new Random());
        assertArrayEquals(sort, reverse);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortRandom() {
        for (int i = 0; i < RANDOM_ITERATIONS; i++) {
            Sorting.quickSort(rand[i], comp, new Random());
            assertArrayEquals(sort, rand[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortSorted() {
        Sorting.mergeSort(sorted, comp);
        assertArrayEquals(sort, sorted);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortReverse() {
        Sorting.mergeSort(reverse, comp);
        assertArrayEquals(sort, reverse);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortRandom() {
        for (int i = 0; i < RANDOM_ITERATIONS; i++) {
            Sorting.mergeSort(rand[i], comp);
            assertArrayEquals(sort, rand[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRadixSortSorted() {
        Sorting.lsdRadixSort(sortedInt);
        assertArrayEquals(sortInt, sortedInt);
    }

    @Test(timeout = TIMEOUT)
    public void testRadixSortReverse() {
        Sorting.lsdRadixSort(reverseInt);
        assertArrayEquals(sortInt, reverseInt);
    }

    @Test(timeout = TIMEOUT)
    public void testRadixSortRandom() {
        for (int i = 0; i < RANDOM_ITERATIONS; i++) {
            Sorting.lsdRadixSort(randInt[i]);
            assertArrayEquals(sortInt, randInt[i]);
        }
    }

    /**
     * Class for testing proper sorting.
     */
    private static class TeachingAssistant {
        private int value;

        /**
         * Create a teaching assistant.
         *
         * @param value value of TA
         */
        public TeachingAssistant(int value) {
            this.value = value;
        }

        /**
         * Get the value of the teaching assistant.
         *
         * @return value of teaching assistant
         */
        public int getValue() {
            return value;
        }

        /**
         * Set the value of the teaching assistant.
         *
         * @param value value of the teaching assistant
         */
        public void setName(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Value: " + value;
        }

        /**
         * Create a comparator that compares the values of the teaching
         * assistants.
         *
         * @return comparator that compares the values of the teaching assistants
         */
        public static ComparatorPlus<TeachingAssistant> getValueComparator() {
            return new ComparatorPlus<TeachingAssistant>() {
                @Override
                public int compare(TeachingAssistant ta1,
                        TeachingAssistant ta2) {
                    incrementCount();
                    return ta1.getValue() - ta2.getValue();
                }
            };
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Reset the number of comparisons made to 0.
         */
        public void resetCount() {
            count = 0;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }
}
