import java.util.Scanner;

public class Main {
    public static String calc(String input) {
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неверный формат ввода");
        }

        int a;
        int b;
        String operator = tokens[1];
        boolean isRoman = false;

        try {
            a = Integer.parseInt(tokens[0]);
            b = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException e) {
            a = RomanNumeral.valueOf(tokens[0]).getValue();
            b = RomanNumeral.valueOf(tokens[2]).getValue();
            isRoman = true;
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
        }

        int result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Неверный оператор");
        };

        if (isRoman) {
            if (result < 1) {
                throw new IllegalArgumentException("Римские числа могут быть только положительными");
            }
            return toRomanNumeral(result);
        } else {
            return String.valueOf(result);
        }
    }

    public static String toRomanNumeral(int number) {
        String result = "";
        int tens = number / 10;
        int ones = number % 10;

        switch (tens) {
            case 1 -> result += "X";
            case 2 -> result += "XX";
            case 3 -> result += "XXX";
            case 4 -> result += "XL";
            case 5 -> result += "L";
            case 6 -> result += "LX";
            case 7 -> result += "LXX";
            case 8 -> result += "LXXX";
            case 9 -> result += "XC";
            case 10 -> result += "C";
        }

        switch (ones) {
            case 1 -> result += "I";
            case 2 -> result += "II";
            case 3 -> result += "III";
            case 4 -> result += "IV";
            case 5 -> result += "V";
            case 6 -> result += "VI";
            case 7 -> result += "VII";
            case 8 -> result += "VIII";
            case 9 -> result += "IX";
            case 10 -> result += "X";
        }

        return result;
    }

    enum RomanNumeral {
        I(1), II(2), III(3), IV(4), V(5),
        VI(6), VII(7), VIII(8), IX(9), X(10);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пример 2 + 2");
        String str = scanner.nextLine();
        String result = calc(str);
        System.out.println(result);
    }
}
