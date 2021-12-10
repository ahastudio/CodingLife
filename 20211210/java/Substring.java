import java.util.Scanner;

public class Substring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        scanner.close();

        Substring substring = new Substring();
        String result = substring.substring(text, start, end);

        System.out.println(result);
    }

    public String substring(String text, final int start, final int end) {
        int offset = Math.max(start, 0);
        int length = Math.min(end, text.length()) - offset;

        String result = "";
        for (int i = 0; i < length; i++) {
            result += text.charAt(offset + i);
        }
        return result;
    }
}
