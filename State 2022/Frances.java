import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Frances {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("frances.dat"));

		int limit = Integer.parseInt(in.nextLine());

		for (int q = 0; q < limit; q++) {
			String needed = in.nextLine();
			HashMap<String, Class> classes = new HashMap<>();
			String[] k = needed.split(",");
			for (String x : k) {
				Class a = new Class(x);
				classes.put(x, a);
			}

			String line = in.nextLine();
			String[] pres = line.split(",");
			for (String x : pres) {
				String pre = x.split("->")[0];
				String post = x.split("->")[1];

				classes.get(post).add(pre);
			}

			line = in.nextLine();
			in.nextLine();

			ArrayList<String> comp = new ArrayList<>();
			boolean v = true;
			String[] order = line.split(",");
			for (String x : order) {
				if (comp.contains(x)) {
					v = false;
					break;
				}
				comp.add(x);
				if (classes.get(x).size() != 0) {
					v = false;
					break;
				}

				for (String h : classes.keySet())
					classes.get(h).remove(x);

			}

			ArrayList<String> required = new ArrayList<>();
			for (String x : needed.split(",")) {
				required.add(x);
			}

			if (v)
				v = comp.containsAll(required);

			System.out.println("Degree plan #" + (q + 1) + " is " + (v ? "legal" : "illegal") + ".");
		}

		in.close();
	}
}

class Class {
	ArrayList<String> pre = new ArrayList<>();
	String name;

	public Class(String name) {
		this.name = name;
	}

	public void add(String a) {
		pre.add(a);
	}

	public int size() {
		return pre.size();
	}

	public void remove(String a) {
		for (int i = 0; i < pre.size(); i++) {
			if (pre.get(i).equals(a)) {
				pre.remove(i);
				break;
			}
		}
	}
}