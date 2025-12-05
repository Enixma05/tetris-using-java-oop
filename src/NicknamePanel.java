import java.awt.*;
import javax.swing.*;

public class NicknamePanel extends JPanel {

    private final TetrisFrame frame;
    private JTextField nickField;

    private final Color bgTop = new Color(253, 190, 240);
    private final Color bgBottom = new Color(148, 106, 235);
    private final Color fieldBG = new Color(255, 245, 255);
    private final Color fieldFG = new Color(50, 37, 76);
    private final Color accent = new Color(155, 130, 188);

    public NicknamePanel(TetrisFrame frame) {
        this.frame = frame;

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Enter Nickname");
        label.setFont(new Font("Segoe UI", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(label, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        JLabel lblNick = new JLabel("Nickname:");
        lblNick.setForeground(Color.WHITE);
        lblNick.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        add(lblNick, gbc);

        nickField = new JTextField();
        nickField.setPreferredSize(new Dimension(200, 50));
        nickField.setOpaque(true);
        nickField.setBackground(fieldBG);
        nickField.setForeground(fieldFG);
        nickField.setCaretColor(fieldFG);
        nickField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        nickField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accent, 2, true),
                BorderFactory.createEmptyBorder(10, 12, 8, 12)
        ));
        gbc.gridx = 1;
        add(nickField, gbc);

        JButton cont = new JButton("Continue");
        cont.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cont.setForeground(Color.WHITE);
        cont.setFocusPainted(false);
        cont.setBorderPainted(false);
        cont.setOpaque(true);
        cont.setBackground(new Color(110, 77, 214));
        cont.setPreferredSize(new Dimension(200, 50));
        cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        gbc.gridy = 2; gbc.gridx = 0; gbc.gridwidth = 2;
        add(cont, gbc);

        cont.addActionListener(e -> {
            String nick = nickField.getText().trim();
            if (nick.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nickname cannot be empty");
                return;
            }
            TetrisFrame.currentNickname = nick;
            frame.showPanel("menu");
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, bgTop, 0, getHeight(), bgBottom);
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
