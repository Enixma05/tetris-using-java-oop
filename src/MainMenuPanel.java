import java.awt.*;
import javax.swing.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(TetrisFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title = new JLabel("Tetris Menu");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);

        JButton start = new JButton("Start Game");
        gbc.gridy = 1;
        add(start, gbc);

        JButton top = new JButton("Leaderboard");
        gbc.gridy = 2;
        add(top, gbc);

        JButton changeNick = new JButton("Change Nickname");
        gbc.gridy = 3;
        add(changeNick, gbc);

        start.addActionListener(e -> frame.startGame());
        top.addActionListener(e -> frame.showLeaderboard());
        changeNick.addActionListener(e -> frame.showPanel("nick"));
    }
}
