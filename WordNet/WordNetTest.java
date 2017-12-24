import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class WordNetTest {

    @Test
    public void WordNetTest(String synsets, String hypernyms) {
        // The constructor should throw a java.lang.IllegalArgumentException 
        // if the input does not correspond to a rooted DAG

        try {
            WordNet wn = new WordNet(null, "test");
        } catch (IllegalArgumentException anIllegalArgumentException) {
            // do nothing
        }

        try {
            WordNet wn = new WordNet(null, "test");
        } catch (IllegalArgumentException anIllegalArgumentException) {
            // do nothing
        }

        try {
            WordNet wn = new WordNet(null, "test");
        } catch (IllegalArgumentException anIllegalArgumentException) {
            // do nothing
        }
    }

    @Test
    public void nounsTest() {
        WordNet wn1 = new WordNet("test/synsets6.txt", "test/hypernyms6TwoAncestors.txt");
        Iterable<String> it1 = wn1.nouns();
        Set<String> setFromIt1 = new HashSet<String>();
        Set<String> setConstruct1 = new HashSet<String>();
        for (String s : it1) {
           setFromIt1.add(s); 
        }
        setConstruct1.add("one");
        setConstruct1.add("two");
        setConstruct1.add("three");
        setConstruct1.add("four");
        setConstruct1.add("five");
        setConstruct1.add("six");
        assertTrue(setFromIt1.equals(setConstruct1));
    
// 
//         WordNet wn2 = new WordNet("test/synsets8.txt", "test/hypernyms8ManyAncestors.txt");
//         assertInteger(wn1.nouns(), 8);
// 
//         WordNet wn3 = new WordNet("test/synset100-subgraph.txt", "test/hypernyms100-subgraphtxt");
//         assertInteger(wn1.nouns(), 100);
// 
//         WordNet wn4 = new WordNet("test/synset1000-subgraph.txt", "test/hypernyms1000-subgraphtxt");
//         assertInteger(wn1.nouns(), 1000);
// 
//         WordNet wn5 = new WordNet("test/synset10000-subgraph.txt", "test/hypernyms10000-subgraphtxt");
//         assertInteger(wn1.nouns(), 10000);
// 
//         WordNet wn6 = new WordNet("test/synsets.txt", "test/hypernyms.txt");
//         assertInteger(wn1.nouns(), 82192);
    }

    @Test
    public void isNounTest() {
        WordNet wn1 = new WordNet("test/synsets6.txt", "test/hypernyms6TwoAncestors.txt");
        assertTrue(wn1.isNoun("one"));
        assertTrue(wn1.isNoun("six"));
        assertFalse(wn1.isNoun("seven"));

        WordNet wn2 = new WordNet("test/synsets8.txt", "test/hypernyms8ManyAncestors.txt");
        assertTrue(wn2.isNoun("one"));
        assertTrue(wn2.isNoun("six"));
        assertTrue(wn2.isNoun("eight"));
        assertFalse(wn2.isNoun("nine"));
    }

    @Test
    public void distanceTest() {
        // The method should throw a java.lang.IllegalArgumentException
        // unless both of the noun arguments are WordNet nouns
    }

    @Test
    public void sapTest() {
        // The method should throw a java.lang.IllegalArgumentException
        // unless both of the noun arguments are WordNet nouns
    }
}
