import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Dmitry {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(new File("dmitry.dat"));

		int m = Integer.parseInt(in.nextLine());

		for (int i = 0; i < m; i++) {

			String[] s = in.nextLine().split(" ");

			int[] arr = new int[s.length];

			for (int j = 0; j < s.length; j++) {
				arr[j] = Integer.parseInt(s[j]);
			}

			Arrays.sort(arr);

			for (int k : arr) {
				for (int x = 0; x < k; x++) {
					System.out.print("X");
				}
				System.out.println();
			}

		}

	}

}
