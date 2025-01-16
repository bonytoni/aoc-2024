import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day3.txt"));
        String line = br.readLine();
        int sum = 0;
        int enabledSum = 0;
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        sum += doMultiplications(sb.toString());
        enabledSum += doEnabledMultiplications(sb.toString());
        System.out.println("1: " + sum);
        System.out.println("2: " + enabledSum);
    }

    // Part 1
    public static int doMultiplications(String line){
        int sum = 0;
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(line);
        while(matcher.find()){
            String mul = matcher.group();
            int comma = mul.indexOf(',');
            int openParentheses = mul.indexOf('(');
            int num1 = Integer.parseInt(mul.substring(openParentheses+1, comma));
            int num2 = Integer.parseInt(mul.substring(comma+1, mul.length()-1));
            sum += num1*num2;
        }
        return sum;
    }

    // Part 2
    public static int doEnabledMultiplications(String line){
        int sum = 0;
        Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(line);
        boolean enabled = true;
        while(matcher.find()){
            String str = matcher.group();
            if (str.equals("do()")){
                enabled = true;
            }
            else if (str.equals("don't()")){
                enabled = false;
            }
            else{
                if (enabled){
                    int comma = str.indexOf(',');
                    int openParentheses = str.indexOf('(');
                    int num1 = Integer.parseInt(str.substring(openParentheses+1, comma));
                    int num2 = Integer.parseInt(str.substring(comma+1, str.length()-1));
                    sum += num1*num2;
                }
            }
        }
        return sum;
    }
}
