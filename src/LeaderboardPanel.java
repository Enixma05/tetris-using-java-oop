import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LeaderboardPanel extends JPanel {

    private DefaultTableModel model;

    private JButton modernButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(232, 143, 67));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 200, 140), 2, true));
        btn.setPreferredSize(new Dimension(180, 45));
        return btn;
    }

    private JLabel modernTitle(String text) {
        JLabel lb = new JLabel(text, SwingConstants.CENTER);
        lb.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lb.setForeground(Color.WHITE);
        return lb;
    }

    public LeaderboardPanel(TetrisFrame frame) {

        setLayout(new BorderLayout());
        setOpaque(false);

        add(modernTitle("Leaderboard"), BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Nickname", "Score", "Date"}, 0);

        JTable table = new JTable(model);
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(158, 106, 133));
        table.getTableHeader().setForeground(Color.WHITE);

        table.setBackground(new Color(170, 163, 198));
        table.setForeground(Color.BLACK);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton back = modernButton("Back");
        add(back, BorderLayout.SOUTH);

        back.addActionListener(e -> frame.backToMenu());
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

    public void loadLeaderboard() {
        model.setRowCount(0);
        for (Object[] row : KoneksiDatabase.getTopTen()) {
            model.addRow(row);
        }
    }
}
