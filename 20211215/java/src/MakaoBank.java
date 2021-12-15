// Example #4

// 1. (static) main, (non-static) run
// 2. Swing -> window(JFrame) -> menu (buttons)
// 3. 기능 목록 = menu = button 목록
//    1) 잔액 조회
//    2) 송금
//    3) 거래 내역 조회
// 4. 객체 -> 실제로 만질 수 있는 것 = 명확히 보이는 것
//        -> Scanner 등은 좀 애매한 것이었다.
//        -> Collection(다른 것들을 가지고 있는 객체)
// 5. List (타입) (추상) - ArrayList (구현체) (구체)
//    사람              - 선생님
//    사람              - 학생
//    List<담을 아이템의 타입> 리스트 = new ArrayList<>();
// 6. Content 변경 -> 3가지가 준비되고, 버튼 누르면 바꿔서 보여줌.
// 7. 우리만의 Panel -> 실제로도 JPanel과 같은 것이길 바람.
//                 -> JPanel이란 타입.
//    타입 (추상) (범용) -> 구현 (구체) (상세하다) (덧붙임) (확장 -> extends -> 상속)
// 8. 생성자 (new 하면 실행되는 메서드)
//    -> 생성자도 그냥 특별한 메서드에 불과하다. 반환(return)이 없다.
//    -> 생성자도 parameters를 지정할 수 있다.
//    -> 의존하는 객체를 받아서 쓸 수 있다. => 의존성 주입 (DI, Dependency Injection)
//                                  => 제어의 역전 (IoC)
//                                  -> 관심사의 분리
//    -> 의존하는 객체를 바꾸는 일은 흔치 않아서 final로 지정.
// 9. 통장/계좌/Account -> 실제로 은행에서 쓰는 용어.
//                    -> 분야=도메인, 도메인 모델, 도메인 객체.
//                    => 핵심이 되는 부분!
// 10. 가장 중요한 부분 = 도메인 객체를 테스트하자. 잘 봐서...
//     -> 어떤 걸 실행하면, 어떤 결과가 나오는지 알고, 그대로 되는지 지켜본다.

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MakaoBank {
    private Account account;

    private JFrame frame;
    private JPanel contentPanel;

    public static void main(String[] args) {
        MakaoBank application = new MakaoBank();
        application.run();
    }

    public void run() {
        account = new Account("123-4567-8901-23", 1000);

        frame = new JFrame("Makao Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        initMenu();
        initContentPanel();

        frame.setVisible(true);
    }

    public void initMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        frame.add(panel, BorderLayout.PAGE_START);

        panel.add(createAmountButton());
        panel.add(createTransferButton());
        panel.add(createTransactionsButton());
    }

    public JButton createAmountButton() {
        JButton button = new JButton("잔액 조회");
        button.addActionListener(event -> {
            JPanel amountPanel = new AmountPanel(account);
            showContentPanel(amountPanel);
        });
        return button;
    }

    public JButton createTransferButton() {
        JButton button = new JButton("송금");
        button.addActionListener(event -> {
            JPanel transferPanel = new TransferPanel(account);
            showContentPanel(transferPanel);
        });
        return button;
    }

    public JButton createTransactionsButton() {
        JButton button = new JButton("거래 내역");
        button.addActionListener(event -> {
            JPanel transactionsPanel = new TransactionsPanel(account);
            showContentPanel(transactionsPanel);
        });
        return button;
    }

    public void initContentPanel() {
        contentPanel = new JPanel();
        frame.add(contentPanel);
    }

    public void showContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);

        contentPanel.setVisible(false);
        contentPanel.setVisible(true);

        frame.setVisible(true);
    }
}
