import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.*;

public class Michelle {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("michelle.dat"));

		int times = Integer.parseInt(in.nextLine());
		for (int run = 0; run < times; run ++) {
			ArrayList<Integer> maxes = new ArrayList<>();
			
			String line = in.nextLine();
			for(String x : line.split(" ")) {
				maxes.add(Integer.parseInt(x));
			}
			maxes.add(50);
			
			String total = "";
			while(true) {
				line = in.nextLine();
				if(line.equals("#"))
					break;
				total+=line+" ";
			}
			int words = 0;
			
			int[] count = new int[maxes.size()];
			for(String x : total.split(" ")) {
				words++;
				for(int i = 0; i < maxes.size(); i++) {
					if(x.length()<=maxes.get(i)) {
						count[i]++;
						break;
					}
				}
			}
			maxes.add(0, 0);
			
			System.out.println("Test case #"+(run+1));
			for(int q = 1; q < maxes.size(); q++) {
				System.out.printf("%02d:%02d -> %-3d   ", maxes.get(q-1)+1, maxes.get(q), Math.round(count[q-1]*100.0/words));
				for(int i = 0; i < Math.round(count[q-1]*100.0/words); i++) {
					System.out.print("x");
				}
				System.out.println();
			}
			
			System.out.println("===============");
			
		}
	/*	int m = Integer.parseInt(in.nextLine());

		for (int i = 0; i < m; i++) {

			String[] s = in.nextLine().split(" ");

			int[][] ls = new int[s.length + 1][2];

			for (int j = 0; j < s.length; j++) {

				ls[j][0] = Integer.parseInt(s[j]);

			}
			ls[s.length][0] = 50;

			LinkedList<String> words = new LinkedList<>();

			while (true) {
				String s2 = in.nextLine();
				if(s2.equals("#")) {
					break;
				} else {
				
				for(String ss : s2.split(" ")) {
					ss = ss.trim();
				
					words.add(ss);
				}
				}
			}
			
			System.out.println(words.toString());
			
			System.out.println(Arrays.deepToString(ls));

			int tot = words.size();

			for (int j = 0; j < words.size(); j++) {

				int cur = words.get(j).strip().length();

				for (int k = 0; k <s.length-1; k++) {
					
					 if (cur <= ls[k][0]) {
						if(k != 0) {
						ls[k-1][1]++;
						} else {
							ls[k][1]++;
						}
						break;
					}
				}

				if (cur > ls[s.length - 1][0]) {
					ls[s.length][1]++;
				}

			}
			
			for(int j = 0; j < s.length; j++) {
				if(ls[j][1] != 0) {
				ls[j][1] = (ls[j][1] * 100) / tot;
				} 
			}
			
			System.out.println(Arrays.deepToString(ls));

		}
	*/
	}

}
