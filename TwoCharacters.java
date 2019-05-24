import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class TwoCharacters {

    // generate all combinations of a string using recursion
    static void generateCombo(List<String> combinations, StringBuffer combo, String originalString, int start) {

        for (int i = start; i < originalString.length(); i++) {
            combo.append(originalString.charAt(i));

            if (combo.length() == originalString.length() - 2) {
                combinations.add(combo.toString());
            }

            // concat the string further to get more combination
            if (i < originalString.length()) {
                generateCombo(combinations, combo, originalString, i + 1);
            }

            combo.deleteCharAt(combo.length() - 1);
        }
    }

    // Complete the alternate function below.
    static int alternate(String s) {

        if (s.length() <= 1)
            return 0;
        else if (s.length() == 2)
            return 2;

        int longestCount = 0;

        // get the string's unique characters
        Set<Character> uniqueChar = new HashSet<>();
        
        for (int i = 0; i < s.length(); i++) {
            uniqueChar.add(s.charAt(i));
        }

        //System.out.println(uniqueChar);
        //System.out.println(uniqueChar.size());

        // recombine the string from set to StringBuffer for easy access
        StringBuffer strippedString = new StringBuffer();

        for (Character c : uniqueChar)
            strippedString.append(c);

        //System.out.println(strippedString);

        List<String> combinations = new ArrayList<>();
        StringBuffer combo = new StringBuffer();

        // generate all the combinations of letters to remove
        generateCombo(combinations, combo, strippedString.toString(), 0);

        //System.out.println(combinations);

        // 
        for (int i = 0; i < combinations.size(); i++) {
            String temp = s;
            String lettersToRemove = combinations.get(i);

            for (int j = 0; j < lettersToRemove.length(); j++) {
                temp = temp.replaceAll(lettersToRemove.charAt(j) + "", "");
            }

            //System.out.println(temp);

            // check if the string alternates
            boolean isAlternating = true;
            char letter1 = temp.charAt(0);
            char letter2 = temp.charAt(1);

            for (int k = 2; k < temp.length(); k++) {
                if (k % 2 == 0) {
                    if (temp.charAt(k) != letter1)
                        isAlternating = false;
                } else {
                    if (temp.charAt(k) != letter2)
                        isAlternating = false;
                }
            }

            // change longestCount is string does alternate back and forth
            if (isAlternating) {
                if (longestCount < temp.length())
                    longestCount = temp.length();
                //System.out.println(temp + " " + longestCount);
            }
        }

        return longestCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}