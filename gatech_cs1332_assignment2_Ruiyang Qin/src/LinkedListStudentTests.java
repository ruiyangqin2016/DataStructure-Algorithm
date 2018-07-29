import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * This is a basic set of unit tests for SinglyLinkedList. Passing these does
 * NOT guarantee any grade on this assignment. This is only a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * @author The 1332 TAs
 * @version 1.0
 */
public class LinkedListStudentTests {
    private LinkedListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void test() {
        /*
        Test addAtIndex
         */

//        list.addAtIndex("a", 0);
//        list.addAtIndex("b", 1);
//        list.addAtIndex("c", 2);
//        list.addAtIndex("d", 3);
//        System.out.println(Arrays.toString(list.toArray()));

        /*
        Test addToFront
         */
        list.addToFront("1");
        System.out.println("Test1: "+ Arrays.toString(list.toArray()));
        System.out.println("Test2: "+ list.removeFromBack());
        list.addToFront("2");
        list.addToFront("3");
        list.addToFront("4");
        list.addToFront("5");
        System.out.println("Test3: "+ Arrays.toString(list.toArray()));

        /*
        Test addToBack
         */
        list.addToBack("10");
        list.addToBack("11");
        list.addToBack("12");
        list.addToBack("13");
        list.addToBack("14");
        list.addToBack("15");
        System.out.println(Arrays.toString(list.toArray()));

        /*
        Test removeAtIndex
         */
        System.out.println(list.removeAtIndex(0));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.removeAtIndex(2));
        System.out.println(Arrays.toString(list.toArray()));

        /*
        Test removeFromFront
         */
        System.out.println(list.removeFromFront());
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.removeFromFront());
        System.out.println(Arrays.toString(list.toArray()));

        /*
        Test removeLastOccurrence
         */
        list.addAtIndex("1", 5);
        System.out.println(list.removeLastOccurrence("13"));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.removeLastOccurrence("0"));
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println(list.get(1));

        /*
        Test clear
         */
        list.clear();
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test(timeout = TIMEOUT)
    public void testAddStrings() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex("0a", 0); //0a
        list.addAtIndex("1a", 1); //0a 1a
        list.addAtIndex("2a", 2); //0a 1a 2a
        list.addAtIndex("3a", 3); //0a 1a 2a 3a
        list.addAtIndex("4a", 1);


        System.out.println(Arrays.toString(list.toArray()));
        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        System.out.println(current.getData());
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); //5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("2a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        list.removeLastOccurrence("2a");
        System.out.println(Arrays.toString(list.toArray()));

        list.addToBack("6a");
        System.out.println(Arrays.toString(list.toArray()));


//        System.out.println(list.removeFromFront());
//        System.out.println(Arrays.toString(list.toArray()));


//        assertEquals("2a", list.removeAtIndex(6)); //0a 1a 3a 4a 5a
//        System.out.println(Arrays.toString(list.toArray()));
//
//        assertEquals("3a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a
//        System.out.println(Arrays.toString(list.toArray()));
//
//        assertEquals("4a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a
//        System.out.println(Arrays.toString(list.toArray()));
//        assertEquals(5, list.size());
//
//        LinkedListNode<String> current = list.getHead();
//        assertNotNull(current);
//        assertEquals("0a", current.getData());
//
//        current = current.getNext();
//        assertNotNull(current);
//        assertEquals("1a", current.getData());
//
//        current = current.getNext();
//        assertNotNull(current);
//        assertEquals("3a", current.getData());
//
//        current = current.getNext();
//        assertNotNull(current);
//        assertEquals("4a", current.getData());
//
//        current = current.getNext();
//        assertNotNull(current);
//        assertEquals("5a", current.getData());
//
//        current = current.getNext();
//        assertNotNull(current);
//        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = "a" + x;
            list.addToBack(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @Test(timeout = TIMEOUT)
    public void removeLastOccurrenceNullItemPassed() {

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("4a", 5);
        list.addToFront("5a");
        System.out.println(Arrays.toString(list.toArray()));
        assertEquals("5a", list.removeAtIndex(0));
        System.out.println(Arrays.toString(list.toArray()));
    }
}