import java.util.Scanner;

class Hello {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        System.out.println();

        System.out.println(3 + 2);
        System.out.println(3 - 2);
        System.out.println(3 * 2);
        System.out.println(3 / 2);

        // double
        System.out.println(3.0 / 2);

        System.out.println();

        // http://wtf.org/
        System.out.println(((Object)3.0).getClass());
        System.out.println(((Object)3.0f).getClass());
        System.out.println(Double.class.isInstance(3.0));
        System.out.println(Float.class.isInstance(3.0f));

        System.out.println();

        int a = 3;
        int b = 2;

        System.out.println(a);
        System.out.println(b);
        System.out.println(a + ", " + b);

        System.out.println();

        // Wrong!
        System.out.println(a + " + " + b + " = " + a + b);

        // Fix
        System.out.println(a + " + " + b + " = " + (a + b));

        System.out.println();

        System.out.println("-".repeat(80));

        // Input

        System.out.print("What's your name? ");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        scanner.close();

        System.out.println("Hello, " + name + "!");
    }
}
