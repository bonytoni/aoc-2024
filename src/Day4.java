import java.io.BufferedReader;
import java.io.FileReader;

public class Day4 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("resources/day4.txt"));
        String line = br.readLine();
        char[][] arr = new char[140][line.length()];
        int row = 0;
        while (line != null) {
            for (int col = 0; col < line.length(); col++){
                arr[row][col] = line.charAt(col);
            }
            row++;
            line = br.readLine();
        }
        int countXMAS = 0;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[0].length; j++){
                if (arr[i][j] == 'X'){
                    countXMAS += findXMAS(arr, i-1, j, new StringBuilder("MAS"), "L")
                            + findXMAS(arr, i+1, j, new StringBuilder("MAS"), "R")
                            + findXMAS(arr, i, j-1, new StringBuilder("MAS"), "T")
                            + findXMAS(arr, i, j+1, new StringBuilder("MAS"), "B")
                            + findXMAS(arr, i-1, j-1, new StringBuilder("MAS"), "TL")
                            + findXMAS(arr, i-1, j+1, new StringBuilder("MAS"), "TR")
                            + findXMAS(arr, i+1, j-1, new StringBuilder("MAS"), "BL")
                            + findXMAS(arr, i+1, j+1, new StringBuilder("MAS"), "BR");
                }
            }
        }
        int count2MAS = 0;
        for (int i = 1; i < arr.length-1; i++){
            for (int j = 1; j < arr[0].length-1; j++){
                if (arr[i][j] == 'A'){
                    count2MAS += find2MAS(arr, i, j);
                }
            }
        }
        System.out.println("1: " + countXMAS);
        System.out.println("2: " + count2MAS);
    }

    // Part 1
    public static int findXMAS(char[][] arr, int row, int col, StringBuilder remainingChars, String direction){
        if (remainingChars.isEmpty()){
            return 1;
        }
        if (row < 0 || row >= arr.length || col < 0 || col >= arr[0].length){
            return 0;
        }
        if (arr[row][col] == remainingChars.charAt(0)) {
            remainingChars.deleteCharAt(0);
            return switch (direction) {
                case "L" -> findXMAS(arr, row - 1, col, remainingChars, "L");
                case "R" -> findXMAS(arr, row + 1, col, remainingChars, "R");
                case "T" -> findXMAS(arr, row, col - 1, remainingChars, "T");
                case "B" -> findXMAS(arr, row, col + 1, remainingChars, "B");
                case "TL" -> findXMAS(arr, row - 1, col - 1, remainingChars, "TL");
                case "TR" -> findXMAS(arr, row - 1, col + 1, remainingChars, "TR");
                case "BL" -> findXMAS(arr, row + 1, col - 1, remainingChars, "BL");
                case "BR" -> findXMAS(arr, row + 1, col + 1, remainingChars, "BR");
                default -> 0;
            };
        }
        return 0;
    }

    // Part 2
    public static int find2MAS(char[][] arr, int row, int col){
        boolean diag1 = (arr[row-1][col-1] == 'M' && arr[row+1][col+1] == 'S' ||
                arr[row-1][col-1] == 'S' && arr[row+1][col+1] == 'M');
        boolean diag2 = (arr[row-1][col+1] == 'M' && arr[row+1][col-1] == 'S' ||
                arr[row-1][col+1] == 'S' && arr[row+1][col-1] == 'M');
        return diag1 && diag2 ? 1 :0;
    }
}
