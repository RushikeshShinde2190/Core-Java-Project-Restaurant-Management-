import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIStyles {

    // Colors
    public static final Color BG = new Color(18,18,18);
    public static final Color CARD = new Color(25,25,25);
    public static final Color PRIMARY = new Color(0,150,136);
    public static final Color HOVER = new Color(255,87,34);
    public static final Color TEXT = Color.WHITE;

    // Rounded card
    public static JPanel card(int arc) {
        return new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                super.paintComponent(g);
            }
        };
    }

    // Modern button with hover + slight scale feel
    public static JButton button(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setForeground(TEXT);
        b.setBackground(PRIMARY);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createEmptyBorder(10,16,10,16));

        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(HOVER);
                b.setBorder(BorderFactory.createEmptyBorder(9,15,9,15));
            }
            public void mouseExited(MouseEvent e) {
                b.setBackground(PRIMARY);
                b.setBorder(BorderFactory.createEmptyBorder(10,16,10,16));
            }
        });
        return b;
    }

    public static JLabel title(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(TEXT);
        l.setFont(new Font("Segoe UI", Font.BOLD, 22));
        return l;
    }

    public static JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(TEXT);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return l;
    }

    public static JTextField input() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        return f;
    }

    public static JComboBox<String> combo() {
        JComboBox<String> c = new JComboBox<>();
        c.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return c;
    }
}