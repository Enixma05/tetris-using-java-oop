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
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        KoneksiDatabase.initialize();

        layout = new CardLayout();
        container = new JPanel(layout);

        nicknamePanel = new NicknamePanel(this);
        menuPanel = new MainMenuPanel(this);
        leaderboardPanel = new LeaderboardPanel(this);

        container.add(nicknamePanel, "nick");
        container.add(menuPanel, "menu");
        container.add(leaderboardPanel, "leaderboard");

        add(container);

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
