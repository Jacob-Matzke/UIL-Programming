import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Richard {

	static int term;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("Richard.dat"));

		final int LIMIT = Integer.parseInt(in.nextLine());
		for (int e = 0; e < LIMIT; e++) {
			term = 0;
			term = Integer.parseInt(in.nextLine())-1;
			String letters = in.nextLine();
			ArrayList<Character> chars = new ArrayList<>();

			for (char x : letters.toCharArray())
				chars.add(x);
			Collections.sort(chars);

			final int TERM = term;
			int len = chars.size();
			final int SIZE = len;
			String solution = "";
			for (int i = 0; i < SIZE; i++) {
				len = chars.size();
				if (len == 2) {
					if (TERM % 2 != 0)
						solution +=""+ chars.get(1) + chars.get(0);
					
					else
						solution +=""+ chars.get(0) + chars.get(1);
					break;
				}
				BigInteger fact = factorial(len - 1);
				int index = BigInteger.valueOf(term).divide(fact).intValue();
				solution += chars.get(index);
				chars.remove(index);
				term = BigInteger.valueOf((term)).mod(fact).intValue();
			}

			System.out.println("Password #"+(e+1)+": "+solution);
		}

		in.close();
	}

	public static BigInteger factorial(int a) {
		if (a == 0)
			return BigInteger.ONE;
		BigInteger toRet = BigInteger.valueOf((long) a);
		for (int i = a - 1; i > 0; i--)
			toRet = toRet.multiply(BigInteger.valueOf(i));
		return toRet;
	}
}
