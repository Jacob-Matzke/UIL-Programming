import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Manuel {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("manuel.dat"));

		int limit = Integer.parseInt(in.nextLine());
		for (int q = 0; q < limit; q++) {
			int nEq = Integer.parseInt(in.nextLine());
			String[] equations = new String[nEq];
			for (int i = 0; i < nEq; i++) {
				equations[i] = in.nextLine();
			}

			if (equations.length == 2) {
				solve2D(equations[0], equations[1]);
			} else {
				solve3D(equations[0], equations[1], equations[2]);
			}
		}

		in.close();
	}

	public static void solve2D(String a, String b) {
		char[] variables = new char[2];
		int c = 0;
		for (int i = 0; i < a.length(); i++) {
			if (Character.isLetter(a.charAt(i))) {
				variables[c] = a.charAt(i);
				c++;
			}
		}
		StringBuilder str = new StringBuilder(a);
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i)) || str.charAt(i) == '=')
				str.replace(i, i + 1, " ");
		}
		a = str.toString();
		str.setLength(0);
		str.append(b);
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i)) || str.charAt(i) == '=')
				str.replace(i, i + 1, " ");
		}
		b = str.toString();
		a = a.replace("  ", " ");
		b = b.replace("  ", " ");

		double a1 = Double.parseDouble(a.split(" ")[0]);
		double b1 = Double.parseDouble(a.split(" ")[1]);
		double c1 = Double.parseDouble(a.split(" ")[2]);

		double a2 = Double.parseDouble(b.split(" ")[0]);
		double b2 = Double.parseDouble(b.split(" ")[1]);
		double c2 = Double.parseDouble(b.split(" ")[2]);

		double deter = (a1 * b2 - b1 * a2);

		double xSol = (c1 * b2 - b1 * c2) / deter;
		double ySol = (a1 * c2 - c1 * a2) / deter;

		if (xSol == -0.0)
			xSol = 0;
		if (ySol == -0.0)
			ySol = 0;

		System.out.printf("%c=%.3f,%c=%.3f\n", variables[0], xSol, variables[1], ySol);
	}

	public static void solve3D(String a, String b, String c) {
		char[] variables = new char[3];
		int k = 0;
		for (int i = 0; i < a.length(); i++) {
			if (Character.isLetter(a.charAt(i))) {
				variables[k] = a.charAt(i);
				k++;
			}
		}
		StringBuilder str = new StringBuilder(a);
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i)) || str.charAt(i) == '=')
				str.replace(i, i + 1, " ");
		}
		a = str.toString();
		str.setLength(0);
		str.append(b);
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i)) || str.charAt(i) == '=')
				str.replace(i, i + 1, " ");
		}
		b = str.toString();
		str.setLength(0);
		str.append(c);
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i)) || str.charAt(i) == '=')
				str.replace(i, i + 1, " ");
		}
		c = str.toString();

		a = a.replace("  ", " ");
		b = b.replace("  ", " ");
		c = c.replace("  ", " ");

		double a1 = Double.parseDouble(a.split(" ")[0]);
		double b1 = Double.parseDouble(a.split(" ")[1]);
		double c1 = Double.parseDouble(a.split(" ")[2]);
		double d1 = Double.parseDouble(a.split(" ")[3]);

		double a2 = Double.parseDouble(b.split(" ")[0]);
		double b2 = Double.parseDouble(b.split(" ")[1]);
		double c2 = Double.parseDouble(b.split(" ")[2]);
		double d2 = Double.parseDouble(b.split(" ")[3]);

		double a3 = Double.parseDouble(c.split(" ")[0]);
		double b3 = Double.parseDouble(c.split(" ")[1]);
		double c3 = Double.parseDouble(c.split(" ")[2]);
		double d3 = Double.parseDouble(c.split(" ")[3]);

		double deter = (a1 * (b2 * c3 - c2 * b3)) - (a2 * (b1 * c3 - c1 * b3)) + (a3 * (b1 * c2 - c1 * b2));

		double xSol = (d1 * (b2 * c3 - c2 * b3) - d2 * (b1 * c3 - c1 * b3) + d3 * (b1 * c2 - b2 * c1)) / deter;
		double ySol = (a1 * (d2 * c3 - c2 * d3) - a2 * (d1 * c3 - c1 * d3) + a3 * (d1 * c2 - c1 * d2)) / deter;
		double zSol = (a1 * (b2 * d3 - b3 * d2) - a2 * (b1 * d3 - b3 * d1) + a3 * (b1 * d2 - d1 * b2)) / deter;

		if (xSol == -0.0)
			xSol = 0;
		if (ySol == -0.0)
			ySol = 0;
		if (zSol == -0.0)
			zSol = 0;

		System.out.printf("%c=%.3f,%c=%.3f,%c=%.3f\n", variables[0], xSol, variables[1], ySol, variables[2], zSol);
	}

}