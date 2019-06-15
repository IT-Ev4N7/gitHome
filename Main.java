import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String str = "-15e78";
        System.out.println(isNumber(str));
    }

    public static boolean isNumber(String s) {
        s = s.toLowerCase();
        String degree = "e";
        List<String> arrayOfNumber;
        arrayOfNumber = Arrays.asList("0123456789".split(""));
        boolean isNum = isContainsNum(s, arrayOfNumber);
        boolean isDegree = s.contains(degree);
        arrayOfNumber = Arrays.asList("0");
        if (!isNum){
            return false;
        }
        if (isDegree && isNum) {
            arrayOfNumber = Arrays.asList(s.split("e"));
            if (!arrayOfNumber.get(0).equals("")) {
                if (arrayOfNumber.get(0).charAt(arrayOfNumber.get(0).length() - 1) == ' ') {
                    return false;
                }
            } else {
                return false;
            }
            s = refactorLine(s);
            arrayOfNumber = Arrays.asList(s.split("e"));
            for (int i = 0; i < arrayOfNumber.size(); i++) {
                if (arrayOfNumber.size() > 2) {
                    return false;
                }
                if (arrayOfNumber.get(i).equals("")) {
                    arrayOfNumber.remove(i);

                }
            }
        }
        if (arrayOfNumber.size() > 2) {
            return false;
        } else if (arrayOfNumber.size() == 2) {
            String[] arr = s.split("");
            int countDegree = 0;
            for (String i : arr) {
                if (i.equals("e")) {
                    countDegree++;
                }
            }
            if (countDegree > 1) {
                return false;
            }
        }
        if (isNum) {
            StringBuilder valStr = new StringBuilder(s);
            if (isDegree) {
                valStr = new StringBuilder(arrayOfNumber.get(0));
            }
            try {
                long varible = Long.parseLong(valStr.toString());
            } catch (Exception eInteger) {
                try {
                    double val = Double.parseDouble(valStr.toString());
                } catch (Exception eDouble) {
                    return false;
                }
            }
        } else {
            return false;
        }
        if (isDegree) {
            try {
                long varible = Long.parseLong(arrayOfNumber.get(1));
            } catch (Exception e) {
                if (arrayOfNumber.size() == 2) {
                    if (arrayOfNumber.get(1).charAt(0) != '0') {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private static String refactorLine(String s) {
        int countSpace = 0;
        StringBuilder newStr = new StringBuilder(s);
        for (int i = 0; i < newStr.length(); i++) {
            if (newStr.toString().charAt(i) == ' ') {
                newStr.deleteCharAt(i);
                i--;
                countSpace++;
            }
        }
        if (countSpace > 0) {
            int indexT = 0;
            String[] array = s.split("");
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(".")) {
                    indexT = i;
                    break;
                }
            }
            try {
                if (array[indexT + 1].equals(" ")) {
                    return s;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return newStr.toString();
            }
        }
        return newStr.toString();
    }

    public static boolean isContainsNum(String str, List<String> nums) {
        int isNum = 0;
        for (String i : nums) {
            if (str.contains(i)) {
                isNum++;
                break;
            }
        }
        nums = Arrays.asList("abcdfghijklmnopqrstuvwxyz".split(""));
        for (String i : nums) {
            if (str.contains(i)) {
                isNum++;
                break;
            }
        }
        if (isNum == 1) {
            return true;
        } else {
            return false;
        }
    }
}
