import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Sample JUnit tests for Homework 1.
 * @version 1
 */
public class ArrayListStudentTests {

    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStrings() {

        assertEquals(0, list.size());

        list.addAtIndex(0, "1");
        list.addAtIndex(1, "2");
        list.addAtIndex(2, "3");
        list.addAtIndex(3, "4");
        list.addAtIndex(4, "5");


        list.addAtIndex(0, "6");
        list.addAtIndex(1, "7");
        list.addAtIndex(2, "8");
        list.addAtIndex(3, "9");
        list.addAtIndex(4, "10");
        list.addToFront("a");
        list.addToFront("b");
        list.addToFront("c");
        System.out.println(Arrays.toString(list.getBackingArray()));
//        System.out.println(list.removeAtIndex(10));
//        System.out.println(list.size());
//
//        System.out.println(Arrays.toString(list.getBackingArray()));
//        System.out.println(list.removeFromFront());
        System.out.println(list.size());


        System.out.println(list.removeFromBack());
        System.out.println(list.size());

        System.out.println(Arrays.toString(list.getBackingArray()));
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a"); //4a 3a 2a 1a 0a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[4] = "4a";
        expected[3] = "3a";
        expected[2] = "2a";
        expected[1] = "1a";
        expected[0] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a
        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a"); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFront() {

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");

        list.removeFromFront(); //0a
        list.removeFromFront(); //0a 1a
        list.removeFromFront(); //0a 1a 2a
        list.removeFromFront(); //0a 1a 2a 3a

        System.out.println(Arrays.toString(list.getBackingArray()));
    }
}
