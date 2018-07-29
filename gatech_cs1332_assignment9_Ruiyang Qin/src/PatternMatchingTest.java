import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PatternMatchingTest {
    private CharacterComparator comp;
    @Before
    public void setUp() throws Exception {
        comp = new CharacterComparator();
    }
    @Test
    public void basicTest() {
        String text = "The quick brown fox jumped over the laxy dog";
        String pattern = "quick";
        ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(4));
        assertEquals(answer, PatternMatching.bruteForce(pattern, text, comp));
        assertEquals(44, comp.getCount());
        reset();
        assertEquals(answer, PatternMatching.boyerMoore(pattern, text, comp));
        assertEquals(13, comp.getCount());
        reset();
        assertEquals(answer, PatternMatching.kmp(pattern, text, comp));
        assertEquals(44, comp.getCount());
        reset();
        text = "aaaaa";
        pattern = "a";
        answer = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        assertEquals(answer, PatternMatching.bruteForce(pattern, text, comp));
        assertEquals(5, comp.getCount());
        reset();
        assertEquals(answer, PatternMatching.boyerMoore(pattern, text, comp));
        assertEquals(5, comp.getCount());
        reset();
        assertEquals(answer, PatternMatching.kmp(pattern, text, comp));
        assertEquals(5, comp.getCount());
        reset();
    }
    @Test
    public void exceptionTesting() {
        try {
            PatternMatching.bruteForce(null, "a", comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.bruteForce("a", null, comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.bruteForce("a", "a", null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.bruteForce("", "a", comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.boyerMoore(null, "a", comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.boyerMoore("a", null, comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.boyerMoore("a", "a", null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.boyerMoore("", "a", comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.kmp(null, "a", comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.kmp("a", null, comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.kmp("a", "a", null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.kmp("", "a", comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.buildFailureTable(null, comp);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.buildFailureTable("a", null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            PatternMatching.buildLastTable(null);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }
    @Test
    public void edgeCaseTest() {
        String text = "Whoop";
        String pattern = "Whoops";
        ArrayList<Integer> answer = new ArrayList<>();
        assertEquals(answer, PatternMatching.bruteForce(pattern, text, comp));
        assertEquals(answer, PatternMatching.boyerMoore(pattern, text, comp));
        assertEquals(answer, PatternMatching.kmp(pattern, text, comp));
        assertEquals(0, comp.getCount());
        text = "Whoops";
        answer = new ArrayList<>(Arrays.asList(0));
        assertEquals(answer, PatternMatching.bruteForce(pattern, text, comp));
        assertEquals(6, comp.getCount());
        reset();
        assertEquals(answer, PatternMatching.boyerMoore(pattern, text, comp));
        assertEquals(6, comp.getCount());
        reset();
        assertEquals(answer, PatternMatching.kmp(pattern, text, comp));
        assertEquals(11, comp.getCount());
        reset();
        pattern = "leer";
        text = "rrrrrrrrrleer";
        answer = new ArrayList<>(Arrays.asList(9));
        assertEquals(answer, PatternMatching.boyerMoore(pattern, text, comp));
        assertEquals(17, comp.getCount());
        reset();
        text = "ababababababa";
        pattern = "ababa";
        answer = new ArrayList<>(Arrays.asList(0, 2, 4, 6, 8));
        assertEquals(answer, PatternMatching.kmp(pattern, text, comp));
        assertEquals(17, comp.getCount());
        reset();
        text = "shellsellsalesaloonsolesoul";
        pattern = "sole";
        answer = new ArrayList<>(Arrays.asList(19));
        assertEquals(answer, PatternMatching.bruteForce(pattern, text, comp));
        assertEquals(33, comp.getCount());
        reset();
        assertEquals(answer, PatternMatching.boyerMoore(pattern, text, comp));
        assertEquals(16, comp.getCount());
        reset();
        assertEquals(answer, PatternMatching.kmp(pattern, text, comp));
        assertEquals(33, comp.getCount());
        reset();
    }
    private void reset() {
        comp = new CharacterComparator();
    }
}