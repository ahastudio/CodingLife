// Baseball Game
// https://www.hackster.io/hyun-woo-park/baseball-game-daecdd

import java.util.Scanner;

class Baseball {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Shuffle

        int[] numbers = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        for (int i = 0; i < 100; i++) {
            int a = (int)(Math.random() * 10);
            int b = (int)(Math.random() * 10);
            int temp = numbers[a];
            numbers[a] = numbers[b];
            numbers[b] = temp;
        }

        // Generate answers

        int[] answers = new int[3];

        for (int i = 0; i < 3; i++) {
            answers[i] = numbers[i];
        }

        System.out.println(
                "[%d %d %d]".formatted(answers[0], answers[1], answers[2]));

        // Prepare inputs

        int[] inputs = new int[3];

        while (true) {
            // Input numbers

            System.out.println();

            System.out.println("Guess numbers!");

            for (int i = 0; i < 3; i++) {
                inputs[i] = scanner.nextInt();
            }

            // Count strike and ball

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

            // Show result

            System.out.println();

            if (strike > 0) {
                System.out.print(strike + " strike ");
            }

            if (ball > 0) {
                System.out.print(ball + " ball ");
            }

            System.out.println();

            // Finish!

            if (strike == 3) {
                System.out.println();
                System.out.println("Yes, you got it!");
                break;
            }
        }

        scanner.close();
    }
}
