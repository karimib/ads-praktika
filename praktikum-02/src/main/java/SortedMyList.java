import java.util.Collections;

public class SortedMyList<T extends Comparable> extends MyList {


    public SortedMyList() {
    }



    private class ComparableNode<T extends Comparable<T>> extends Node implements Comparable<T>  {

        @Override
        public int compareTo(T o) {
        }
    }
}
