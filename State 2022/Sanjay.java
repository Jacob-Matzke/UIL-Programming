import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sanjay {
	
	public static double shortest;
	static boolean isValid;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("sanjay.dat"));

		int limit = (int) in.nextDouble();
		for (int q = 0; q < limit; q++) {
			int numIs = (int) in.nextDouble();
			double sX = in.nextDouble();
			double sY = in.nextDouble();
			double dX = in.nextDouble();
			double dY = in.nextDouble();

			double m = dY / dX;
			double yInt = sY - m * sX;

			ArrayList<Island> islands = new ArrayList<>();
			double angle = Math.abs(Math.atan(m));
			for (int i = 0; i < numIs; i++) {
				int vertices = (int) in.nextDouble();
				islands.add(new Island(vertices));
				for (int k = 0; k < vertices; k++)
					islands.get(i).addPoint(in.nextDouble(), in.nextDouble());
			}
			
			ArrayList<Island> valids = new ArrayList<>();
			islands.forEach(is -> {
				isValid = false;
				is.points.forEach(pt -> {
					double nY = (double)pt.y;
					double nX = (double)pt.x;
					if(!(((dX > 0 ? nX < sX : nX > sX)) || (dY > 0 ? nY < sY : nY > sY))) {
						isValid = true;
					}
				});
				if(isValid)
					valids.add(is);
			});
			
			shortest = Double.MAX_VALUE;
			valids.forEach(is -> {
				double newSlope = 0;
				double newIntercept = 0;
				int numbV = is.numbVerts;
				boolean intercepts = false;
				for(int i = 0; i < numbV; i++) {
					Point p1 = is.points.get(i);
					Point p2 = is.points.get((i+1)%numbV);
					
					newSlope = ((double)p1.y-(double)p2.y)/((double)p1.x-(double)p2.x);
					newIntercept = (double)p1.y-newSlope*(double)p1.x;
					
					double at = Solution(newSlope, newIntercept, m, yInt);
					if(at >= (double)p1.x && at <= (double)p2.x || at >= (double)p2.x && at <= (double)p1.x)
						intercepts = true;
				}
				if(intercepts)
					shortest = 0;
			});
			
			
			if(shortest != 0) {
				islands.forEach(is -> {
					is.points.forEach(pt -> {
						double nY = (double)pt.y;
						double nX = (double)pt.x;
						double newM = (nY-yInt)/nX;
						double distance = -1;
						if(((dX > 0 ? nX < sX : nX > sX)) || (dY > 0 ? nY < sY : nY > sY)) {
							distance = Math.sqrt(Math.pow(nX-sX, 2)+Math.pow(nY-sY, 2));
						} else {
							if(Math.signum(newM) != Math.signum(m)) {
								double newAng = Math.abs(Math.atan(newM));
								double actAngle = Math.PI/2-newAng;
								double length = Math.sqrt(Math.pow(nX, 2)+Math.pow(nY-yInt, 2));
								distance = length*Math.cos(actAngle);
							} else {
								double newAng = Math.abs(Math.atan(newM));
								double difAng = Math.abs(newAng-angle);
								double length = Math.sqrt(nX*nX+Math.pow(nY-yInt, 2));
								distance = length*Math.sin(difAng);
							}
						}
						shortest = shortest < distance ? shortest : distance;
					});
				});
			}
			
			System.out.printf("%.3f\n", shortest);
		}

		in.close();
	}
	
	public static double Solution(double m1, double y1, double m2, double y2) {
		return (y2-y1)/(m1-m2);
	}
}

class Island {
	int numbVerts;
	ArrayList<Point> points = new ArrayList<>();
	
	public Island(int a) {
		numbVerts = a;
	}

	public void addPoint(double a, double b) {
		points.add(new Point(a, b));
	}
	
	@Override
	public String toString() {
		return numbVerts + " "+points.toString();
	}
}

class Point<K, V> {
	public K x;
	public V y;

	public Point(K x, V y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return x + ", "+y;
	}
	
	
}