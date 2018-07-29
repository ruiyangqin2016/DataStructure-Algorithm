import java.util.Arrays;

public class Test {

    public static int p(int a) {
        if (a == 0 || a == 1) {
            return 3;
        } else {
            return p(a - 1) * p(a - 2);
        }
    }

    public static String a(int b) {
        if (b % 2 == 0) { //Even number
            if (b == 2) {
                return "--";
            } else {
                return "+" + a(b - 2) + "+";
            }
        } else { // odd number
            if (b == 1) {
                return "-";
            } else {
                return "+" + a(b - 2) + "+";
            }
        }
    }
    /**
     *
     * @param args args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 0; i++) {
            System.out.println("test");
        }
//        System.out.println(a(11));
//        DoublyLinkedList<String> list = new DoublyLinkedList<>();
//        list.addToFront("a");
//        System.out.println(list.removeFromBack());
//        list.addToFront("b");
//        list.addToFront("c");
//        list.addToFront("d");
//        list.addToFront("e");
//        System.out.println(Arrays.toString(list.toArray()));
//        System.out.println(list.removeAtIndex(1));
//        System.out.println(list.removeFromBack());

//        System.out.println(Arrays.toString(list.toArray()));

//        list.addToBack("1");
//        list.addToBack("2");
//        list.addToBack("3");
//        list.addToBack("4");
//        list.addToBack("5");
//        list.addToBack("6");
//        System.out.println(Arrays.toString(list.toArray()));
//
//        list.addAtIndex("A", 4);
//        System.out.println(Arrays.toString(list.toArray()));
    }
}
