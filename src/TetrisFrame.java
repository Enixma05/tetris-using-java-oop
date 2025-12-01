import java.awt.*;
import javax.swing.*;

public class TetrisFrame extends JFrame {

    public static String currentNickname = "";
    private CardLayout layout;
    private JPanel container;

    private NicknamePanel nicknamePanel;
    private MainMenuPanel menuPanel;
    private Board boardPanel;
    private LeaderboardPanel leaderboardPanel;

    public TetrisFrame() {

        setTitle("Tetris Game");
        setSize(380, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        KoneksiDatabase.initialize();

        layout = new CardLayout();
        container = new JPanel(layout);
        container.setOpaque(false);

        nicknamePanel = new NicknamePanel(this);
        menuPanel = new MainMenuPanel(this);
        leaderboardPanel = new LeaderboardPanel(this);

        container.add(nicknamePanel, "nick");
        container.add(menuPanel, "menu");
        container.add(leaderboardPanel, "leaderboard");

        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(50, 37, 76),
                    0, getHeight(), new Color(155, 130, 188)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(container, BorderLayout.CENTER);

        showPanel("nick");
    }

    public void showPanel(String name) {
        layout.show(container, name);
    }

    public void startGame() {
        if (boardPanel != null)
            container.remove(boardPanel);

        boardPanel = new Board(currentNickname, this);
        container.add(boardPanel, "game");
        showPanel("game");
        boardPanel.start();
    }

    public void showLeaderboard() {
        leaderboardPanel.loadLeaderboard();
        showPanel("leaderboard");
    }

    public void backToMenu() {
        showPanel("menu");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TetrisFrame().setVisible(true));
    }
}
