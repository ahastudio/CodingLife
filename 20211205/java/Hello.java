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

        System.out.println("Hello, " + name + "!");

        // Baseball Game
        // https://www.hackster.io/hyun-woo-park/baseball-game-daecdd

        System.out.println("-".repeat(80));

        int[] answers = new int[]{ 0, 0, 0 };

        for (int i = 0; i < 3; i++) {
            answers[i] = (int)(Math.random() * 10);
            System.out.println((i + 1) + " - " + answers[i]);
        }

        int[] inputs = new int[3];

        while (true) {
            System.out.println("Guess numbers!");

            for (int i = 0; i < 3; i++) {
                inputs[i] = scanner.nextInt();
            }

            int strike = 0;
            int ball = 0;

            for (int i = 0; i < 3; i++) {
              for (int j = 0; j < 3; j++) {
                if (inputs[i] == answers[j]) {
                  if (i == j) {
                    strike += 1;
                    continue;
                  }
                  ball += 1;
                }
              }
            }

            if (strike > 0) {
                System.out.print(strike + " strike ");
            }
            if (ball > 0) {
                System.out.print(ball + " ball ");
            }
            System.out.println();

            if (strike == 3) {
                System.out.println("Yes, you got it!");
                break;
            }
        }

        scanner.close();
    }
}
