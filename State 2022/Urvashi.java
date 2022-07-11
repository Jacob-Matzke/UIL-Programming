import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Urvashi {

	static ArrayList<Node> allKids = new ArrayList<>();
	static int endI;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("urvashi.dat"));

		final int LIMIT = Integer.parseInt(in.nextLine());
		for (int q = 0; q < LIMIT; q++) {
			String want = in.nextLine();
			int roads = Integer.parseInt(want.split(" ")[0]);
			int casts = Integer.parseInt(want.split(" ")[1]);
			String start = want.split(" ")[2];
			String end = want.split(" ")[3];

			String[] connections = new String[roads];
			for (int i = 0; i < roads; i++)
				connections[i] = in.nextLine();

			ArrayList<String> cities = new ArrayList<>();
			for (String x : connections) {
				for (int i = 0; i < 2; i++) {
					String a = x.split(" ")[i];
					if (!cities.contains(a))
						cities.add(a);
				}
			}

			int numbCities = cities.size();
			int[][] adjMat = new int[numbCities][numbCities];
			for (int[] x : adjMat)
				Arrays.fill(x, 0);

			for (String x : connections) {
				int a = cities.indexOf(x.split(" ")[0]);
				int b = cities.indexOf(x.split(" ")[1]);

				int cost = Integer.parseInt(x.split(" ")[2]);
				adjMat[a][b] = cost;
				adjMat[b][a] = cost;
			}
			
			boolean hasSolution = false;

			int startI = cities.indexOf(start);
			endI = cities.indexOf(end);

			Node startNode = new Node(null, startI, 0);
			for (int i = 0; i < numbCities; i++) {
				allKids.clear();
				findBaseNodes(startNode);
				for (Node x : allKids) {
					int curIndex = x.id;
					if(curIndex == -1)
						continue;
					int[] adjcnes = adjMat[curIndex];
					for (int y = 0; y < adjcnes.length; y++) {
						int k = adjcnes[y];
						if (k == 0 || x.isDuplicateParent(y))
							continue;
						if (y == endI) {
							hasSolution = true;
							x.addNode(new Node(x, -1, k));
						}else
							x.addNode(new Node(x, y, k));
					}
				}
			}
			
			if(!hasSolution) {
				System.out.println("IMPOSSIBLE");
				continue;
			}
			allKids.clear();
			findBaseNodes(startNode);
			
			for(int i = 0; i < allKids.size();) {
				if(allKids.get(i).id != -1) {
					allKids.remove(i);
				} else {
					i++;
				}
			}
			
			ArrayList<ArrayList<Integer>> solutionSets = new ArrayList<>();
			
			for(Node x : allKids) {
				ArrayList<Integer> costs = new ArrayList<>();
				while(x.parent != null) {
					costs.add(x.cost);
					x = x.parent;
				}
				solutionSets.add(costs);
			}
			
			int lowest = Integer.MAX_VALUE;
			StringBuilder str = new StringBuilder();
			for(int i = 0; i < solutionSets.size(); i++) {
				int cost = 0;
				HashMap<Integer, Integer> shouldFlipU = new LinkedHashMap<>();
				ArrayList<Integer> ar = solutionSets.get(i);
				for(int x : ar) {
					str.setLength(0);
					str.append(Integer.toString(x));
					str.reverse();
					int reverse = Integer.parseInt(str.toString());
					shouldFlipU.put(x, reverse-x);
				}
				HashMap<Integer, Integer> shouldFlip = new LinkedHashMap<>();
				shouldFlipU.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x -> shouldFlip.put(x.getKey(), x.getValue()));
				int count = 0;
				for(Entry<Integer, Integer> e : shouldFlip.entrySet()) {
					if(count < casts) {
						if(e.getValue() > 0) {
							cost+=e.getKey();
							continue;
						}
						cost+=e.getKey()+e.getValue();
						count++;
					} else {
						cost+=e.getKey();
					}
				}
				
				lowest = lowest < cost ? lowest : cost;
			}
			
			System.out.printf("Case #%d: %d\n", (q+1), lowest);
		}

		in.close();
	}

	public static void findBaseNodes(Node node) {
		if (node.hasChildren()) {
			for (Node x : node.children)
				findBaseNodes(x);
		} else {
			allKids.add(node);
		}
	}
}

class Node {
	public Node parent;
	public int level;
	public int id;
	public int cost;
	public ArrayList<Node> children = new ArrayList<>();

	public Node(Node parent, int id, int cost) {
		if (parent == null)
			level = 0;
		else
			level = parent.level + 1;
		this.parent = parent;
		this.id = id;
		this.cost = cost;
	}

	public void addNode(Node child) {
		children.add(child);
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}

	public boolean isDuplicateParent(int tId) {
		if (parent == null)
			return false;
		if (parent.id == tId)
			return true;
		return parent.isDuplicateParent(tId);
	}

	@Override
	public String toString() {
		return (level == 0 ? "root" : parent.id) + "->" + this.id + ":" + this.cost + "->" + this.children;
	}
}