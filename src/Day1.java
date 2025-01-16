import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Day1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day1.txt"));
        List<Integer> leftLst = new ArrayList<>();
        List<Integer> rightLst = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line);
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            leftLst.add(first);
            rightLst.add(second);
            line = br.readLine();
        }
        int totalDistance = getTotalDistanceBetweenLists(leftLst, rightLst);
        System.out.println("1: " + totalDistance);
        int similarityScore = getSimilarityScore(leftLst, rightLst);
        System.out.println("2: " + similarityScore);
    }

    // Part 1
    public static int getTotalDistanceBetweenLists(List<Integer> a, List<Integer> b) {
        Collections.sort(a);
        Collections.sort(b);
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += Math.abs((a.get(i) - b.get(i)));
        }
        return sum;
    }

    // Part 2
    public static int getSimilarityScore(List<Integer> lst1, List<Integer> lst2) {
        Map<Integer, Integer> countsA = new HashMap<>();
        Map<Integer, Integer> countsB = new HashMap<>();
        Set<Integer> setA = new HashSet<>();
        for (int i = 0 ; i < lst1.size(); i++){
            int x = lst1.get(i);
            int y = lst2.get(i);
            countsA.put(x, countsA.getOrDefault(x, 0)+1);
            countsB.put(y, countsB.getOrDefault(y, 0)+1);
            setA.add(x);
        }
        int score = 0;
        for (Integer i : setA) {
            int factor = countsB.getOrDefault(i, 0);
            int occurrences = countsA.get(i);
            score += occurrences * i * factor;
        }
        return score;
    }
}
