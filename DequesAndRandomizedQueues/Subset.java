import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
   public static void main(String[] args) {
       int k = StdIn.readInt();
       RandomizedQueue<String> rq = new RandomizedQueue<String>();
       String s;
       while (null != (s = StdIn.readString())) {
           rq.enqueue(s);
       }

       for (int i = 0; i < k; i++) {
           StdOut.println(rq.sample());
       }
   }
}
