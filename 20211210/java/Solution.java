import java.util.stream.IntStream;

public class Solution {
    private int[][] fares;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        setFares(n, fares);

        return IntStream.range(1, n + 1)
                .map(i -> fare(s, i) + fare(i, a) + fare(i, b))
                .min()
                .getAsInt();
    }

    public void setFares(int n, int[][] fares) {
        this.fares = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.fares[i][j] = i == j ? 0 : Integer.MAX_VALUE / 3;
            }
        }
        for (int[] tuple : fares) {
            int a = tuple[0] - 1;
            int b = tuple[1] - 1;
            int fare = tuple[2];
            this.fares[a][b] = fare;
            this.fares[b][a] = fare;
        }
        for (int x = 0; x < n; x++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int a = this.fares[i][x];
                    int b = this.fares[x][j];
                    if (a + b < this.fares[i][j]) {
                        this.fares[i][j] = a + b;
                        this.fares[i][j] = a + b;
                    }
                }
            }
        }
    }

    public int fare(int start, int end) {
        return this.fares[start - 1][end - 1];
    }
}
