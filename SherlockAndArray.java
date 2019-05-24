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

public class SherlockAndArray {

    static int sum(int start, int end, List<Integer> arr) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += arr.get(i).intValue();
        }
        return sum;
    }

    // Complete the balancedSums function below.
    static String balancedSums(List<Integer> arr) {
        String result = "NO";
        int leftSum = 0, rightSum = 0;

        if (arr.size() == 1)
            return "YES";

        for (int i = 0; i < arr.size(); i++) {

            // avoid calling sum to reduce time complexity
            // make sure we can sum both left and right side
            if (i > 0) {
                leftSum += arr.get(i - 1).intValue();
                rightSum -= arr.get(i).intValue();
            } else {
                // leftSum has nothing
                // righSum has stuff to sum up
                rightSum = sum(i + 1, arr.size(), arr);
            }

            if (leftSum == rightSum) {
                result = "YES";
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                String result = balancedSums(arr);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}