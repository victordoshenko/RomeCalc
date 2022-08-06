import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String ROME_NUMBERS = "I|II|III|IV|V|VI|VII|VIII|IX|X";
    private static final String ARAB_NUMBERS = "\\d|\\d\\d";
    private static boolean isRome;

    public static void main(String[] args) {
        System.out.println("Input expression, please:");
        String expr = new Scanner(System.in).nextLine();
        expr = expr.replaceAll("\\s+", "");

        if (Pattern.matches("(" + ARAB_NUMBERS + ")[/+-/*/](" + ARAB_NUMBERS + ")", expr)) {
            isRome = false;
        } else if (Pattern.matches("(" + ROME_NUMBERS + ")[/+-/*/](" + ROME_NUMBERS + ")", expr)) {
            isRome = true;
        } else {
            System.out.println("throws Error");
            return;
        }

        Pattern p = Pattern.compile("[/+-/*/]");
        Matcher m = p.matcher(expr);
        m.find();
        int operIndex = m.start();
        String oper = expr.substring(operIndex, operIndex + 1);
        String leftArg = expr.substring(0, operIndex);
        String rightArg = expr.substring(operIndex + 1);

        int leftNum = isRome ? romanToNumber(leftArg) : Integer.valueOf(leftArg);
        int rightNum = isRome ? romanToNumber(rightArg) : Integer.valueOf(rightArg);

        if (leftNum < 1 || leftNum > 10 || rightNum < 1 || rightNum > 10) {
            System.out.println("Numbers must be between 1 and 10");
            return;
        }

        int result = 0;
        switch (oper) {
            case "+":
                result = leftNum + rightNum;
                break;
            case "-":
                result = leftNum - rightNum;
                break;
            case "*":
                result = leftNum * rightNum;
                break;
            case "/":
                result = leftNum / rightNum;
                break;
        }

        if (isRome && result <= 0) {
            System.out.println("Error: Roman result must be >= 1");
        } else if (isRome) {
            System.out.println("Result(roman):" + numberToRoman(result));
        } else {
            System.out.println("Result(arabian):" + result);
        }
    }

    private static int romanToNumber(String roman) {
        if (roman.equals("I")) {
            return 1;
        } else if (roman.equals("II")) {
            return 2;
        } else if (roman.equals("III")) {
            return 3;
        } else if (roman.equals("IV")) {
            return 4;
        } else if (roman.equals("V")) {
            return 5;
        } else if (roman.equals("VI")) {
            return 6;
        } else if (roman.equals("VII")) {
            return 7;
        } else if (roman.equals("VIII")) {
            return 8;
        } else if (roman.equals("IX")) {
            return 9;
        } else if (roman.equals("X")) {
            return 10;
        }
        return -1;
    }

    private static String numberToRoman(int numArabian) {
        String[] roman = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }
}
