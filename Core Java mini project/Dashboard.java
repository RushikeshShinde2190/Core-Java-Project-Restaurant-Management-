import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    CardLayout cl = new CardLayout();
    JPanel content = new JPanel(cl);

    public Dashboard() {

        setTitle("Restaurant App");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UIStyles.BG);

        // Sidebar
        JPanel side = new JPanel();
        side.setBackground(new Color(20,20,20));
        side.setLayout(new GridLayout(6,1,10,10));
        side.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        side.setPreferredSize(new Dimension(180,0));

        JButton b1 = UIStyles.button("🍔 Add Food");
        JButton b2 = UIStyles.button("📋 View Menu");
        JButton b3 = UIStyles.button("🛒 Order Food");
        JButton b4 = UIStyles.button("🚪 Logout");

        side.add(new JLabel("   MENU", JLabel.CENTER));
        side.add(b1); side.add(b2); side.add(b3); side.add(b4);

        // Pages
        content.add(new AddFoodPanel(), "add");
        content.add(new ViewMenuPanel(), "view");
        content.add(new OrderFoodPanel(), "order");

        b1.addActionListener(e -> cl.show(content,"add"));
        b2.addActionListener(e -> cl.show(content,"view"));
        b3.addActionListener(e -> cl.show(content,"order"));
        b4.addActionListener(e -> { new LoginScreen(); dispose(); });

        root.add(side, BorderLayout.WEST);
        root.add(content, BorderLayout.CENTER);

        add(root);
        setVisible(true);
    }
}