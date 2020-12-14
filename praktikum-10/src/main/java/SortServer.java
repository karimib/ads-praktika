import java.util.ArrayList;
import java.util.Arrays;

public class SortServer implements CommandExecutor {
	private Integer[] data;

	private String procedure;

	private static final String SELECTION = "SELECTION";
	private static final String BUBBLE = "BUBBLE";
	private static final String INSERTION = "INSERTION";

	public String execute(String command) {
		parseCommand(command);
		if (procedure.equals(INSERTION)) insertionSort(data);
		if (procedure.equals(BUBBLE)) bubbleSort(data);
		if (procedure.equals(SELECTION)) selectionSort(data);
		return Boolean.toString(checkSorted(data));
	}

	private void parseCommand(String command) {
		String[] args = command.split("\\s");
		procedure = args[0];
		data = new Integer[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			data[i - 1] = Integer.valueOf(args[i]);
		}
	}

	private void bubbleSort(Integer[] array) {
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

	private void insertionSort(Integer[] array) {
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

	private void selectionSort(Integer[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[index]) {
					index = j;
				}
				swap(array, array[index], array[i]);
			}
		}

	}

	private void swap(Integer[] array, int i, int j) {
		Integer h = array[i];
		array[i] = array[j];
		array[j] = h;
	}

	private boolean checkSorted(Integer[] array) {
		if (array.length <= 1) return true;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > (array[i + 1])) return false;
		}
		return true;
	}
}

