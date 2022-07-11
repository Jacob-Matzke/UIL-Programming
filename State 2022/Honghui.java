import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Honghui {

    static LinkedList<ColorSet> colors = new LinkedList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("honghui.dat"));

        int limit = Integer.parseInt(in.nextLine());
        for (int q = 0; q < limit; q++) {
            colors.clear();
            int size = Integer.parseInt(in.nextLine());
            String parenthesis = in.nextLine();
            String[] colorsArr = in.nextLine().split(" ");
            String[] values = in.nextLine().split(" ");
            String[] sequence = new String[size];
            for (int i = 0; i < size; i++)
                sequence[i] = colorsArr[i] + (parenthesis.charAt(i) == '(' ? "o" : "c") + values[i];

            for (String x : sequence) {
                int index = indexOf(x);
                if (index == -1) {
                    colors.add(new ColorSet(x));
                    continue;
                }

                colors.get(index).add(x);
            }

            for (int i = 0; i < colors.size();) {
                if (colors.get(i).isBalanced())
                    colors.remove(i);
                else
                    i++;
            }
            int cost = 0;
            for (int i = 0; i < colors.size(); i++)
                cost += colors.get(i).balance();

            System.out.println("Case #" + (q + 1) + ": " + cost);
        }

        in.close();
    }

    public static int indexOf(String x) {
        char c = x.charAt(0);
        for (int q = 0; q < colors.size(); q++) {
            ColorSet cs = colors.get(q);
            if (cs.size() == 0)
                continue;
            if (cs.get() == c)
                return q;
        }

        return -1;
    }
}

class ColorSet {
    LinkedList<String> set = new LinkedList<>();

    public ColorSet(String x) {
        set.add(x);
    }

    public void add(String x) {
        set.add(x);
    }

    public int size() {
        return set.size();
    }

    public Character get() {
        return set.get(0).charAt(0);
    }

    public int indexOfValue(int x) {
        for (int i = 0; i < set.size(); i++)
            if (Integer.parseInt(set.get(i).substring(2)) == x)
                return i;
        return -1;
    }

    public boolean isBalanced() {
        if (set.size() % 2 == 1)
            return false;

        LinkedList<String> copy = new LinkedList<>();
        for (String x : set)
            copy.add(x);
        for (int i = 0; i < copy.size() - 1; i++) {
            char denom1 = copy.get(i).charAt(1);
            if (denom1 != 'o')
                continue;

            for (int q = i + 1; q < copy.size(); q++) {
                char denom2 = copy.get(q).charAt(1);
                if (denom2 != 'c')
                    continue;

                String p1 = copy.get(i);
                String p2 = copy.get(q);

                p1 = p1.charAt(0) + "b" + p1.charAt(2);
                p2 = p2.charAt(0) + "b" + p2.charAt(2);
                copy.set(i, p1);
                copy.set(q, p2);
            }
        }

        for (String x : copy)
            if (x.charAt(1) != 'b')
                return false;
        return true;
    }

    public int balance() {
        if (set.size() == 1)
            return Integer.parseInt(Character.toString(set.get(0).charAt(2)));
        LinkedList<Integer> values = new LinkedList<>();
        for (String x : set)
            values.add(Integer.parseInt(x.substring(2)));
        Collections.sort(values, Collections.reverseOrder());

        for (int q = 0; q < values.size(); q++) {
            char denom1 = set.get(indexOfValue(values.get(q))).charAt(1);
            if (denom1 == 'b')
                continue;
            for (int i = 0; i < values.size(); i++) {
                if (q == i)
                    continue;
                char denom2 = set.get(indexOfValue(values.get(i))).charAt(1);
                if (denom2 == 'b')
                    continue;

                if (denom1 != denom2) {
                    String p1 = set.get(indexOfValue(values.get(q)));
                    String p2 = set.get(indexOfValue(values.get(i)));

                    p1 = p1.charAt(0) + "b" + p1.charAt(2);
                    p2 = p2.charAt(0) + "b" + p2.charAt(2);
                    set.set(indexOfValue(values.get(q)), p1);
                    set.set(indexOfValue(values.get(i)), p2);
                    break;
                }
            }
        }
        int c = 0;
        for (String x : set)
            if (x.charAt(1) != 'b')
                c += Integer.parseInt(x.substring(2));

        return c;
    }
}
