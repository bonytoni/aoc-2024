import java.io.BufferedReader;
import java.io.FileReader;

public class Day2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day2.txt"));
        String line = br.readLine();
        int safeCount = 0;
        int reallySafeCount = 0;
        while (line != null) {
            String[] arr = line.split(" ");
            if (isSafe(arr)) {
                safeCount++;
            }
            if (isReallySafe(arr)) {
                reallySafeCount++;
            }
            line = br.readLine();
        }
        System.out.println("1: " + safeCount);
        System.out.println("2: " + reallySafeCount);
    }

    // Part 1
    public static boolean isSafe(String[] nums) {
        boolean increasing = Integer.parseInt(nums[1]) > Integer.parseInt(nums[0]);
        if (increasing) {
            for (int i = 1; i < nums.length; i++) {
                int difference = Integer.parseInt(nums[i]) - Integer.parseInt(nums[i - 1]);
                if (!(difference >= 1 && difference <= 3)) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < nums.length; i++) {
                int difference = Integer.parseInt(nums[i]) - Integer.parseInt(nums[i - 1]);
                if (!(difference >= -3 && difference <= -1)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Part 2
    public static boolean isReallySafe(String[] nums){
        if (isSafe(nums)){
            return true;
        }
        String[] newArr = new String[nums.length-1];
        for (int removeIndex = 0; removeIndex < nums.length; removeIndex++){
            int newArrIndex = 0;
            for (int i = 0; i < nums.length; i++){
                if (removeIndex != i){
                    newArr[newArrIndex] = nums[i];
                    newArrIndex++;
                }
            }
            if (isSafe(newArr)){
                return true;
            }
        }
        return false;
    }
}