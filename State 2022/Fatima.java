import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Fatima {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("fatima.dat"));

		int limit = (int) in.nextDouble();
		for (int q = 0; q < limit; q++) {
			double aStep = in.nextDouble();
			double hStep = in.nextDouble();

			ArrayList<Double> temps = new ArrayList<>();
			double minTemp = 80;
			while (minTemp <= 125) {
				temps.add(minTemp);
				minTemp += aStep;
			}
			if(!temps.contains(125.0))
				temps.add(125.0);

			Set<Double> humids = new TreeSet<>();
			double minHum = 20;
			while (minHum <= 100) {
				humids.add(minHum);
				minHum += hStep;
			}

			System.out.print("Temp ");
			for (Double x : temps)
				System.out.printf("%6.1f", x);

			System.out.println();
			System.out.print("Humid ");
			for (int i = 0; i < 95; i++)
				System.out.print("=");

			System.out.println();

			for (Double x : humids) {
				System.out.printf("%5.1f", x);
				for(int i = 0;i  < temps.size(); i++) {
					double t = temps.get(i);
					double hi = -42.379 + 2.04901523 * t + 10.14333127 * x - .22475541 * t * x
							- 6.83783 * .001 * t*t - 5.481717 * .01 * x * x
							+ 1.22874 * .001 * t * t * x + 8.5282 * .0001 * t * x * x
							- 1.99 * .000001 * t * t * x * x;
					if(x > 85) {
						hi+= (((x-85.0)/10.0)*((87.0-t)/5.0));
					}
					if(hi > 140)
						break;
					System.out.printf("%6.1f",hi);
				}
				System.out.println();
			}
			
			for(int i = 0; i < 30; i++) {
				System.out.print("=");
			}
			System.out.println();
		}

		in.close();
	}
}
