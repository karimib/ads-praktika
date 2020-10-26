public class KGVServer implements CommandExecutor {
	@Override
	public String execute(String s) {
		String[] numbers = s.split(" ");
		int a = Integer.parseInt(numbers[0]);
		int b = Integer.parseInt(numbers[1]);
		return Integer.toString(kgv(a, b));
	}

	/**
	 * Calculates the greatest common divisor for numbers a,b
	 *
	 * @param a A number
	 * @param b A number
	 * @return gcd(a, b)
	 */

	public int gcd(int a, int b) {
		int gcd = 0;
		while (a != b) {
			if (a > b) {
				a -= b;
			} else {
				b -= a;
			}
			gcd = a;
		}
		return gcd;
	}

	/**
	 * Calculates the least common multiple for numbers a,b
	 *
	 * @param a A number
	 * @param b A number
	 * @return lcm(a, b) DE: kgv(a,b)
	 */

	public int kgv(int a, int b) {
		if (a == 0 || b == 0) {
			return 0;
		} else {
			return Math.abs(a * b / gcd(a, b));
		}

	}
}
