import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    public LoginScreen() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {}

        setTitle("Login");
        setSize(420, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(18,18,18));

        JPanel card = UIStyles.card(25);
        card.setOpaque(false);
        card.setLayout(new GridLayout(4,1,10,10));
        card.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        card.setPreferredSize(new Dimension(300, 220));

        JLabel title = new JLabel("🔐 Login", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextField user = new JTextField("admin");
        JPasswordField pass = new JPasswordField("1234");

        JButton login = UIStyles.button("Login");

        login.addActionListener(e -> {
            if(user.getText().equals("admin") && String.valueOf(pass.getPassword()).equals("1234")){
                new Dashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Invalid Credentials");
            }
        });

        card.add(title);
        card.add(user);
        card.add(pass);
        card.add(login);

        root.add(card);
        add(root);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}