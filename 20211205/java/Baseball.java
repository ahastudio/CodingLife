// Baseball Game
// https://www.hackster.io/hyun-woo-park/baseball-game-daecdd

import java.util.Scanner;

class Baseball {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] answers = new int[]{ 0, 0, 0 };

        for (int i = 0; i < 3; i++) {
            answers[i] = (int)(Math.random() * 10);
        }

        System.out.println(
                "[%d %d %d]".formatted(answers[0], answers[1], answers[2]));

        int[] inputs = new int[3];

        while (true) {
            System.out.println();

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

            System.out.println();

            if (strike > 0) {
                System.out.print(strike + " strike ");
            }

            if (ball > 0) {
                System.out.print(ball + " ball ");
            }

            System.out.println();

            if (strike == 3) {
                System.out.println();
                System.out.println("Yes, you got it!");
                break;
            }
        }

        scanner.close();
    }
}
