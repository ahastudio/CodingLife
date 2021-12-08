// Baseball Game
// https://www.hackster.io/hyun-woo-park/baseball-game-daecdd

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Baseball {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Shuffle

        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(numbers);

        // Generate answers

        List<Integer> answers = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            answers.add(numbers.get(i));
        }

        System.out.println("[%d %d %d]".formatted(
                answers.get(0), answers.get(1), answers.get(2)));

        // Prepare inputs

        List<Integer> inputs = new ArrayList<>();

        while (true) {
            // Input numbers

            System.out.println();

            System.out.println("Guess numbers!");

            inputs.clear();
            for (int i = 0; i < 3; i++) {
                int number = scanner.nextInt();
                inputs.add(number);
            }

            // Count strike and ball

            int strike = 0;
            int ball = 0;

            for (int i = 0; i < 3; i++) {
              for (int j = 0; j < 3; j++) {
                if (inputs.get(i) == answers.get(j)) {
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
