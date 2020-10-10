import java.util.Comparator;

public class SortedMyList<T extends Comparable<T>> extends MyList<T> {


    public SortedMyList() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T obj) {
        boolean success = super.add(obj);
        super.sort(Comparator.naturalOrder());
        return success;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(T obj) {
        boolean success = super.remove(obj);
        super.sort(Comparator.naturalOrder());
        return success;
    }

}

