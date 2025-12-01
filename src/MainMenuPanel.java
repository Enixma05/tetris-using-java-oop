import java.awt.*;
import javax.swing.*;

public class MainMenuPanel extends JPanel {

    private JButton modernButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(232, 143, 67)); // #E98F43
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 200, 140), 2, true));
        btn.setPreferredSize(new Dimension(180, 45));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JLabel modernTitle(String text) {
        JLabel lb = new JLabel(text, SwingConstants.CENTER);
        lb.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lb.setForeground(Color.WHITE);
        return lb;
    }

    public MainMenuPanel(TetrisFrame frame) {
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel title = modernTitle("Tetris Menu");
        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);

        JButton start = modernButton("Start Game");
        gbc.gridy = 1;
        add(start, gbc);

        JButton top = modernButton("Leaderboard");
        gbc.gridy = 2;
        add(top, gbc);

        JButton changeNick = modernButton("Change Nickname");
        gbc.gridy = 3;
        add(changeNick, gbc);

        start.addActionListener(e -> frame.startGame());
        top.addActionListener(e -> frame.showLeaderboard());
        changeNick.addActionListener(e -> frame.showPanel("nick"));
    }

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
}
