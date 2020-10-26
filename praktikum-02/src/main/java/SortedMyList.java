import java.util.Comparator;

public class SortedMyList<T extends Comparable<T>> extends MyList<T> {

	public SortedMyList() {
	}

	@Override
	public boolean add(T obj) {
		boolean success = super.add(obj);
		sort(Comparator.naturalOrder());
		return success;
	}
}

