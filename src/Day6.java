import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<int[]> visitedCoords = new ArrayList<>();
        int visited = 0;
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j++){
                if (solved[i][j] == 9){
                    visited++;
                    int[] coords = new int[2];
                    coords[0] = i;
                    coords[1] = j;
                    visitedCoords.add(coords);
                }
            }
        }
        int loops = 0;
        for (int[] coords : visitedCoords) {
            int[][] temp = makeCopy(arr);
            int i = coords[0];
            int j = coords[1];
            temp[i][j] = 1;
            try {
                navigateArray(temp, startingRow, startingCol);
            } catch (Exception e) {
                if (e.getMessage().equals("loop")) {
                    loops++;
                }
            }
        }
        System.out.println("1: " + visited);
        System.out.println("2: " + loops);
    }

    // Part 1 + Part 2
    public static int[][] navigateArray(int[][] arr, int startingRow, int startingCol) throws Exception{
        int[][] solved = makeCopy(arr);
        int row = startingRow;
        int col = startingCol;
        solved[row][col] = 9;
        String direction = "up";
        boolean run = true;
        Map<String, List<String>> visited = new HashMap<>();
        while (run){
            List<String> visitedDirections = visited.get(row + "," + col);
            if (visitedDirections == null){
                visitedDirections = new ArrayList<>();
                visitedDirections.add(direction);
            }
            else if (!visitedDirections.contains(direction)){
                visitedDirections.add(direction);
            }
            else{
                throw new Exception("loop");
            }
            visited.put(row + "," + col, visitedDirections);
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
                        solved[row][col] = 9;
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
                        solved[row][col] = 9;
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
                        solved[row][col] = 9;
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
                        solved[row][col] = 9;
                    }
                    break;
            }
        }
        return solved;
    }
    public static boolean checkOutOfBounds(int[][] arr, int row, int col){
        return row < 0 || row >= arr.length || col < 0 || col >= arr[0].length;
    }
    public static int[][] makeCopy(int[][] arr){
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                res[i][j] = arr[i][j];
            }
        }
        return res;
    }
}
