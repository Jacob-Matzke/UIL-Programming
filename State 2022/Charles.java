import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Charles {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("charles.dat"));
		
		int lim = in.nextInt();
		for(int q = 0; q < lim; q++) {
			int k = in.nextInt();
			ArrayList<Integer> factors = factor(k);
			int sum = 0;
			for(int i = 0; i < factors.size(); i++) {
				sum+=factors.get(i);
				if(i == factors.size()-1)
					System.out.print(factors.get(i));
				else
					System.out.print(factors.get(i)+"+");
			}
			System.out.println("="+sum);
		}
		
		in.close();
	}
	
	public static ArrayList factor(int a) {
		ArrayList<Integer> fac = new ArrayList<>();
		for(int i = 1; i <= a; i++) {
			if(a % i == 0)
				fac.add(i);
		}
		
		return fac;
	}
}
