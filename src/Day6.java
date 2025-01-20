import java.io.BufferedReader;
import java.io.FileReader;

public class Day6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day6.txt"));
        String line = br.readLine();
        int numRows = 130;
        int numCols = line.length();
        int startingRow = -1;
        int startingCol = -1;
        // 0-empty, 1-obstacle, 9-visited
        int[][] arr = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j++){
                char curr = line.charAt(j);
                if (curr == '#'){
                    arr[i][j] = 1;
                }
                else if (curr == '^'){
                    startingRow = i;
                    startingCol = j;
                }
            }
            line = br.readLine();
        }
        int[][] solved = navigateArray(arr, startingRow, startingCol);
        int visited = 0;
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j++){
                if (solved[i][j] == 9){
                    visited++;
                }
            }
        }
        System.out.println("1: " + visited);
    }

    // Part 1
    public static int[][] navigateArray(int[][] arr, int startingRow, int startingCol){
        int[][] solved = arr.clone();
        int row = startingRow;
        int col = startingCol;
        solved[row][col] = 9;
        String direction = "up";
        boolean run = true;
        while (run){
            switch(direction) {
                case "up":
                    int upRow = row - 1;
                    if (checkOutOfBounds(arr, upRow, col)) {
                        run = false;
                        break;
                    }
                    if (arr[upRow][col] == 1){
                        direction = "right";
                    }
                    else{
                        row--;
                        arr[row][col] = 9;
                    }
                    break;
                case "down":
                    int downRow = row + 1;
                    if (checkOutOfBounds(arr, downRow, col)) {
                        run = false;
                        break;
                    }
                    if (arr[downRow][col] == 1){
                        direction = "left";
                    }
                    else{
                        row++;
                        arr[row][col] = 9;
                    }
                    break;
                case "left":
                    int leftCol = col - 1;
                    if (checkOutOfBounds(arr, row, leftCol)) {
                        run = false;
                        break;
                    }
                    if (arr[row][leftCol] == 1){
                        direction = "up";
                    }
                    else{
                        col--;
                        arr[row][col] = 9;
                    }
                    break;
                case "right":
                    int rightCol = col + 1;
                    if (checkOutOfBounds(arr, row, rightCol)) {
                        run = false;
                        break;
                    }
                    if (arr[row][rightCol] == 1){
                        direction = "down";
                    }
                    else{
                        col++;
                        arr[row][col] = 9;
                    }
                    break;
            }
        }
        return solved;
    }
    public static boolean checkOutOfBounds(int[][] arr, int row, int col){
        return row < 0 || row >= arr.length || col < 0 || col >= arr[0].length;
    }
}
