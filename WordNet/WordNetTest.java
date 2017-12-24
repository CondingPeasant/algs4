import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

public class WordNetTest {

    @Test
    public void WordNetTest(String synsets, String hypernyms) {
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
        
        // The constructor should throw a java.lang.IllegalArgumentException 
        // if the input does not correspond to a rooted DAG
    }

    @Test
    public void nounsTest() {
    }

    @Test
    public void isNounTest() {
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
