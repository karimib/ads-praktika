public class Sorter<T extends Comparable<? super T>> {

	private static final int WORK_THRESHOLD = 50;
	private int threshold;
	private Procedure procedure;


	enum Procedure {
		BUBBLE("bubble"),
		INSERTION("insertion"),
		SELECTION("selection"),
		QUICK("quick"),
		QUICKER("quicker"),
		QUICKER_PARALLEL("quicker_parallel");

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
		this.procedure = Procedure.valueOf(procedure);
		return this;
	}

	public void sort(T[] data) {
		if (data == null) throw new RuntimeException("data cannot be null");
		if (procedure == null) throw new RuntimeException("procedure cannot be null");
		if (isSorted(data)) throw new RuntimeException("data already sorted");
		sort(data, procedure);
	}

	public <T extends Comparable<? super T>> boolean isSorted(T[] data) {
		return data != null && checkSorted(data);
	}


	private <T extends Comparable<? super T>> void sort(T[] data, Procedure procedure) {
		switch (procedure) {
			case BUBBLE:
				bubbleSort(data, 0, data.length - 1);
				break;
			case INSERTION:
				insertionSort(data, 0, data.length - 1);
				break;
			case SELECTION:
				selectionSort(data, 0, data.length - 1);
				break;
			case QUICK:
				quickSort(data, 0, data.length - 1);
				break;
			case QUICKER:
				quickerSort(data, 0, data.length - 1);
				break;
			case QUICKER_PARALLEL:
				Thread root = new ParallelQuickSort<T>(data, 0, data.length - 1);
				root.start();
				try {
					root.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
		}
	}

	private class ParallelQuickSort<T extends Comparable<? super T>> extends Thread {
		private final int SPLIT_THRESHOLD = 4096;
		private final T[] array;
		private final int low;
		private final int high;

		public ParallelQuickSort(T[] array, int low, int high) {
			this.array = array;
			this.low = low;
			this.high = high;
		}

		@Override
		public void run() {
			int mid = 0;
			Thread t1 = null;
			Thread t2 = null;
			if (low < high) {
				mid = partition(array, low, high);
				if (mid - low > SPLIT_THRESHOLD) {
					t1 = new ParallelQuickSort<T>(array, low, mid - 1);
					t1.start();
				} else {
					quickSort(array, low, high);
				}
				if (high - mid > SPLIT_THRESHOLD) {
					t2 = new ParallelQuickSort<T>(array, mid, high);
					t2.start();
				} else {
					quickSort(array, mid, high);
				}
				if (t1 != null) {
					try {
						t1.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (t2 != null) {
					try {
						t2.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}


	}

	private <T extends Comparable<? super T>> int partition(T[] array, int left, int right) {
		T pivot = getMedian(array, left, right);
		while (left <= right) {
			while (array[left].compareTo(pivot) < 0) {
				left++;
			}
			while (array[right].compareTo(pivot) > 0) {
				right--;
			}
			if (left <= right) {
				swap(array, left, right);
				left++;
				right--;
			}
		}
		return left;
	}

	private <T extends Comparable<? super T>> T getMedian(T[] array, int left, int right) {
		int mid = (left + right) / 2;

		if (array[left].compareTo(array[mid]) > 0)
			swap(array, left, mid);

		if (array[left].compareTo(array[right]) > 0)
			swap(array, left, right);

		if (array[mid].compareTo(array[right]) > 0)
			swap(array, mid, right);

		swap(array, mid, right);
		return array[right];
	}

	private <T extends Comparable<? super T>> void quickerSort(T[] array, int low, int high) {
		if (low < high) {
			if (high - low < WORK_THRESHOLD) {
				insertionSort(array, low, high);
			} else {
				int mid = partition(array, low, high);
				quickSort(array, low, mid - 1);
				quickSort(array, mid, high);
			}
		}
	}

	private <T extends Comparable<? super T>> void quickSort(T[] array, int low, int high) {
		if (low < high) {
			int mid = partition(array, low, high);
			quickSort(array, low, mid - 1);
			quickSort(array, mid, high);
		}
	}

	private <T extends Comparable<? super T>> void bubbleSort(T[] array, int low, int high) {
		for (int k = high; k > low; k--) {
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

	private <T extends Comparable<? super T>> void insertionSort(T[] array, int low, int high) {
		for (int j = low; j < high; j++) {
			T key = array[j];
			int i = j - 1;
			while ((i >= low) && (array[i].compareTo(key) > 0)) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
	}

	private <T extends Comparable<? super T>> void selectionSort(T[] array, int low, int high) {
		for (int i = low; i < high; i++) {
			int index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(array[index]) < 0) {
					index = j;
				}
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
