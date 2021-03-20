import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static int add_called_count = 0;

    public int Add(String numbers) {
        add_called_count += 1;

        int sum = 0;
        String delimiterPattern= ",\n";

        // compute string size
        int numbers_size = numbers.length();

        // if we have at least one number, i.e. input string is not empty
        if (numbers_size != 0) {

            String negatives = "";
            int negatives_count = 0;

            if (numbers.indexOf("//") == 0) {
                numbers = numbers.substring(2);

                // get first character
                char firstChar = numbers.charAt(0);

                // if input precedes delimiter followed by a newline character
                if (!(firstChar >= '0' && firstChar <= '9') && (numbers_size > 1)) {

                    if (firstChar == '[') {

                        // get first index of a number
                        Pattern pattern = Pattern.compile("^\\D*(\\d)");
                        Matcher matcher = pattern.matcher(numbers);
                        matcher.find();
                        int idxFirstNumber = matcher.start(1);

                        // find all delimiters of size > 1 until number is seen
                        String delimiterSubstring = numbers.substring(0,idxFirstNumber);
                        Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(delimiterSubstring);
                        while (m.find()) {
                            delimiterPattern = delimiterPattern.concat("(").concat(m.group(1)).concat(")");
                        }

                        numbers = numbers.substring(idxFirstNumber);
                        System.out.println(numbers);

                    } else {
                        char secondChar = numbers.charAt(1);

                        if (secondChar == '\n') {
                            delimiterPattern = delimiterPattern.concat(Character.toString(firstChar));
                            numbers = numbers.substring(2);
                        }
                    }
                }
            }

            // define delimiter regex
            String delimiterPatternFinal = String.format("[%s]", delimiterPattern);

            // extract numbers from string
            String [] numberArray = numbers.split(delimiterPatternFinal);

            // perform addition

            for(String numStr: numberArray) {
                int num = Integer.parseInt(numStr);
                if (num < 1000) {
                    sum += num;
                }

                if(num < 0) {
                    if (negatives_count > 0)
                        negatives = negatives.concat(",");

                    negatives = negatives.concat(numStr);
                    negatives_count += 1;
                }
            }

            if (negatives_count > 0) {
                throw new IllegalArgumentException("Negative numbers not allowed: " + negatives);
            }
        }

        return sum;
    }

    public int GetCalledCount() {
        return add_called_count;
    }
}
