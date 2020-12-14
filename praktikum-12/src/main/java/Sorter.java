public class Sorter<T extends Comparable<? super T>> {

	private int threshold = 50;
	private Procedure appliedProcedure;

	enum Procedure {
		BUBBLE("bubble"), INSERTION("insertion"), SELECTION("selection"), QUICKER("quicker");
		private final String type;

		Procedure(String s) {
			this.type = s.toLowerCase();
		}
	}

	public Sorter() {
	}

	public Sorter<T> withThreshold(int threshold) {
		this.threshold = threshold;
		return this;
	}

	public Sorter<T> withProcedure(String procedure) {
		this.appliedProcedure = Procedure.valueOf(procedure);
		return this;
	}

	public Sorter<T> sort(T[] data) {
		if (data == null) throw new RuntimeException("data cannot be null");
		if (appliedProcedure == null) throw new RuntimeException("appliedProcedure cannot be null");
		sort(data, appliedProcedure, threshold);
		return this;
	}

	public <T extends Comparable<? super T>> boolean isSorted(T[] data) {
		if (data != null && checkSorted(data)) return true;
		return false;
	}

	private <T extends Comparable<? super T>> void sort(T[] data, Procedure procedure, int threshold) {
		switch (procedure) {
			case BUBBLE:
				bubbleSort((T[]) data);
				break;
			case INSERTION:
				insertionSort((T[]) data);
				break;
			case SELECTION:
				selectionSort((T[]) data);
				break;
			case QUICKER:
				quickerSort((T[]) data, threshold);
				break;
		}
	}

	private <T extends Comparable<? super T>> void quickerSort(T[] data, int threshold) {
		if (data.length < threshold) {
			insertionSort(data);
		} else {

		}
	}


	private <T extends Comparable<? super T>> void bubbleSort(T[] array) {
		for (int k = array.length - 1; k > 0; k--) {
			boolean noSwap = true;
			for (int i = 0; i < k; i++) {
				if (array[i].compareTo(array[i + 1]) > 0) {
					swap(array, i, i + 1);
					noSwap = false;
				}
			}
			if (noSwap) break;
		}
	}

	private <T extends Comparable<? super T>> void insertionSort(T[] array) {
		int n = array.length;
		for (int j = 1; j < n; j++) {
			T key = array[j];
			int i = j - 1;
			while ((i > -1) && (array[i].compareTo(key) > 0)) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
	}

	private <T extends Comparable<? super T>> void selectionSort(T[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(array[index]) < 0) index = j;
			}
			swap(array, index, i);
		}

	}

	private <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
		T h = array[i];
		array[i] = array[j];
		array[j] = h;
	}

	private <T extends Comparable<? super T>> boolean checkSorted(T[] array) {
		if (array == null) return false;
		if (array.length <= 1) return true;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i].compareTo(array[i + 1]) > 0) return false;
		}
		return true;
	}

}
