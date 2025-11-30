import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LeaderboardPanel extends JPanel {

    private DefaultTableModel model;

    public LeaderboardPanel(TetrisFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Leaderboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Nickname", "Score", "Date"}, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.addActionListener(e -> frame.backToMenu());
        add(back, BorderLayout.SOUTH);
    }

    public void loadLeaderboard() {
        model.setRowCount(0);
        for (Object[] row : KoneksiDatabase.getTopTen()) {
            model.addRow(row);
        }
    }
}
