import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day7.txt"));
        String line = br.readLine();
        Map<Long, int[]> valToOperands = new HashMap<>();
        while (line != null){
            String[] arr = line.split(" ");
            long val = Long.parseLong(arr[0].substring(0, arr[0].length()-1));
            int[] operands = new int[arr.length-1];
            for (int i = 1; i < arr.length; i++){
                operands[i-1] = Integer.parseInt(arr[i]);
            }
            valToOperands.put(val, operands);
            line = br.readLine();
        }
        long sum1 = 0;
        List<Long> values1 = getTrueEquationValues(valToOperands, 1);
        for (long val : values1){
            sum1 += val;
        }
        long sum2 = 0;
        List<Long> values2 = getTrueEquationValues(valToOperands, 2);
        for (long val : values2){
            sum2 += val;
        }
        System.out.println("1: " + sum1);
        System.out.println("2: " + sum2);
    }
    public static List<Long> getTrueEquationValues(Map<Long, int[]> valToOperands, int part){
        List<Long> values = new ArrayList<>();
        for (long val : valToOperands.keySet()){
            int[] operands = valToOperands.get(val);
            int index = 1;
            int current = operands[0];
            if (part == 1 && isTrueEquation(val, current, index, operands)){
                values.add(val);
            }
            else if (part == 2 && isTrueEquation2(val, current, index, operands)){
                values.add(val);
            }
        }
        return values;
    }
    public static boolean isTrueEquation(long val, long current, int index, int[] operands){
        if (current == val && index == operands.length){
            return true;
        }
        else if (current > val || index == operands.length){
            return false;
        }
        long sum = current + operands[index];
        long prod = current * operands[index];
        index++;
        return isTrueEquation(val, sum, index, operands) || isTrueEquation(val, prod, index, operands);
    }
    public static boolean isTrueEquation2(long val, long current, int index, int[] operands){
        if (current == val && index == operands.length){
            return true;
        }
        else if (current > val || index == operands.length){
            return false;
        }
        long sum = current + operands[index];
        long prod = current * operands[index];
        long concat = Long.parseLong(current +String.valueOf(operands[index]));
        index++;
        return isTrueEquation2(val, sum, index, operands)
                || isTrueEquation2(val, prod, index, operands)
                || isTrueEquation2(val, concat, index, operands);
    }
}
