// Example #3 (부속)

public class CoreCalculator {
    // 상수 (static final) -> 추천은 아니지만, 가끔은 public으로 합니다.
    public static final String[] OPERATORS = new String[]{"+", "-", "*", "/", "="};

    // 프로그램의 핵심 상태
    private long accumulator = 0;
    private long currentNumber = 0;
    private String currentOperator = "";

    // currentNumber의 Getter (추천은 아니지만, 일단은 씁시다)
    public String getCurrentNumber() {
        return Long.toString(currentNumber);
    }

    // accumulator의 Getter (추천은 아니지만, 일단은 씁시다)
    public String getAccumulator() {
        return Long.toString(accumulator);
    }

    // 진짜로 할 수 있는 일!!!

    // 숫자 눌렀을 때 활용
    public void addNumber(String number) {
        currentNumber *= 10;
        currentNumber += Integer.valueOf(number);
    }

    // 연산자 눌렀을 때 활용
    public void updateOperator(String operator) {
        currentOperator = operator;
    }

    // 계산 <-- 가장 핵심!!!
    public void calculate() {
        switch (currentOperator) {
            case "" -> accumulator = currentNumber;
            case "+" -> accumulator += currentNumber;
            case "-" -> accumulator -= currentNumber;
            case "*" -> accumulator *= currentNumber;
            case "/" -> accumulator /= currentNumber;
        }
        currentNumber = 0;
    }
}
