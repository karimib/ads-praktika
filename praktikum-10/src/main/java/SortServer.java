public class SortServer implements CommandExecutor {
	private int[] data;

	public String execute(String command) throws Exception {
		String[] args = command.split("\\s");
		data = args[1].lines().mapToInt(Integer::parseInt).toArray();
		if (args[0] == "INSERTION") {
			insertionSort(data);
		}
		if (args[0] == "BUBBLE") {
			bubbleSort(data);
		}
		if (args[0] == "SELECTION") {
			selectionSort(data);
		}
		return Boolean.toString(checkSorted(data));
	}

	private void measureTimeBubble(int[] in) {
		long end, start = System.currentTimeMillis();
		int count = 0;
		do {
			bubbleSort(in);
			count++;
			end = System.currentTimeMillis();
		} while (end - start < 1000);
		System.out.println("time=" + (double) (end - start) / count);
	}

	private void bubbleSort(int[] array) {
		for (int k = array.length - 1; k > 0; k--) {
			boolean noSwap = true;
			for (int i = 0; i < k; i++) {
				if (array[i] > (array[i + 1])) {
					swap(array, i, i + 1);
					noSwap = false;
				}
			}
			if (noSwap) break;
		}
	}

	private void insertionSort(int[] array) {
		int n = array.length;
		for (int j = 1; j < n; j++) {
			int key = array[j];
			int i = j - 1;
			while ((i > -1) && (array[i] > key)) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
	}

	private void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[index]) {
					index = j;
				}
			}
			int smallerNumber = array[index];
			array[index] = array[i];
			array[i] = smallerNumber;
		}

	}

	private void swap(int[] array, int i, int j) {
		int h = array[i];
		array[i] = array[j];
		array[j] = h;
	}

	private boolean checkSorted(int[] array) {
		if (array.length <= 1) return true;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > (array[i + 1])) {
				return false;
			}
		}
		return true;
	}
}
