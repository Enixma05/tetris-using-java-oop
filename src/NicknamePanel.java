import java.awt.*;
import javax.swing.*;

public class NicknamePanel extends JPanel {
    private final TetrisFrame frame;
    private JTextField nickField;

    public NicknamePanel(TetrisFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel label = new JLabel("Enter Nickname");
        label.setFont(new Font("Arial", Font.BOLD, 26));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(label, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("Nickname:"), gbc);
        nickField = new JTextField(18);
        gbc.gridx = 1;
        add(nickField, gbc);

        JButton cont = new JButton("Continue");
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
}
