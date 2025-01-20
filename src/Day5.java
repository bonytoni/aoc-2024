import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day5 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("resources/day5.txt"));
        String line = br.readLine();
        // key: a number, value: set of numbers that the key must come before
        Map<Integer, Set<Integer>> rulesMap = new HashMap<>();
        List<List<Integer>> updatesList = new ArrayList<>();
        int ruleCount = 1176;
        int updateCount = 204;
        for (int i = 0; i < ruleCount; i++){
            String[] arr = line.split("\\|");
            int key = Integer.parseInt(arr[0]);
            int value = Integer.parseInt(arr[1]);
            Set<Integer> temp;
            if (rulesMap.containsKey(key)){
                temp = rulesMap.get(key);
            }
            else{
                temp = new HashSet<>();
            }
            temp.add(value);
            rulesMap.put(key, temp);
            line = br.readLine();
        }
        line = br.readLine();
        for (int i = 0; i < updateCount; i++){
            String[] arr = line.split(",");
            List<Integer> updates = new ArrayList<>();
            for (String str : arr){
                updates.add(Integer.parseInt(str));
            }
            updatesList.add(updates);
            line = br.readLine();
        }
        List<Integer> middleValues = getMiddleValues(rulesMap, updatesList);
        int sum = 0;
        for (Integer val : middleValues){
            sum += val;
        }
        List<Integer> middleValuesAfterSorting = getMiddleValuesAfterSorting(rulesMap, updatesList);
        int sum2 = 0;
        for (Integer val : middleValuesAfterSorting){
            sum2 += val;
        }
        System.out.println("1: " + sum);
        System.out.println("2: " + sum2);
    }
    // Part 1
    public static List<Integer> getMiddleValues(Map<Integer, Set<Integer>> rulesMap, List<List<Integer>> updatesList){
        List<Integer> middleValues = new ArrayList<>();
        for (List<Integer> list : updatesList){
            if (isCorrectlyOrdered(list, rulesMap)){
                middleValues.add(list.get(list.size()/2));
            }
        }
        return middleValues;
    }
    public static boolean isCorrectlyOrdered(List<Integer> list, Map<Integer, Set<Integer>> rulesMap){
        for (int i = 0; i < list.size()-1; i++){
            Set<Integer> rules = rulesMap.get(list.get(i));
            for (int j = i+1; j < list.size(); j++){
                if (rules == null || !rules.contains(list.get(j))){
                    return false;
                }
            }
        }
        return true;
    }

    // Part 2 for incorrectly-ordered updates
    public static List<Integer> getMiddleValuesAfterSorting(Map <Integer, Set<Integer>> rulesMap, List<List<Integer>> updatesList) {
        List<Integer> middleValues = new ArrayList<>();
        for (List<Integer> list : updatesList){
            if (!isCorrectlyOrdered(list, rulesMap)){
                List<Integer> temp = sortIncorrectList(list, rulesMap);
                middleValues.add(temp.get(temp.size()/2));
            }
        }
        return middleValues;
    }
    public static List<Integer> sortIncorrectList(List<Integer> updatesList, Map <Integer, Set<Integer>> rulesMap){
        int[] updatesArr = new int[updatesList.size()];
        for (int i = 0; i < updatesArr.length; i++){
            updatesArr[i] = updatesList.get(i);
        }
        for (int i = 0; i < updatesArr.length; i++){
            for (int j = 0; j < updatesArr.length-1; j++){
                Set<Integer> rules = rulesMap.get(updatesArr[j]);
                if (rules == null || !rules.contains(updatesArr[j+1])) {
                    int temp = updatesArr[j+1];
                    updatesArr[j+1] = updatesArr[j];
                    updatesArr[j] = temp;
                }
            }
        }
        List<Integer> correctList = new ArrayList<>();
        for (int i = 0; i < updatesArr.length; i++){
            correctList.add(updatesArr[i]);
        }
        return correctList;
    }
}
