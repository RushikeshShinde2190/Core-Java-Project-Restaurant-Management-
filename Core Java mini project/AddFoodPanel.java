import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddFoodPanel extends JPanel {

    public AddFoodPanel() {

        setBackground(UIStyles.BG);
        setLayout(new GridBagLayout());

        JPanel card = UIStyles.card(25);
        card.setOpaque(false);
        card.setLayout(new GridLayout(3,2,10,10));
        card.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        card.setPreferredSize(new Dimension(350,200));

        JTextField name = UIStyles.input();
        JTextField price = UIStyles.input();

        JButton add = UIStyles.button("Add Food");

        card.add(UIStyles.label("Food Name"));
        card.add(name);
        card.add(UIStyles.label("Price"));
        card.add(price);
        card.add(add);

        add(card);

        add.addActionListener(e -> {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/restaurant_db","root","Root@123");

                PreparedStatement check = con.prepareStatement(
                        "SELECT * FROM food_items WHERE name=?");
                check.setString(1,name.getText());

                if(check.executeQuery().next()){
                    JOptionPane.showMessageDialog(this,"Already exists");
                } else {
                    PreparedStatement pst = con.prepareStatement(
                            "INSERT INTO food_items(name,price) VALUES(?,?)");
                    pst.setString(1,name.getText());
                    pst.setDouble(2,Double.parseDouble(price.getText()));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(this,"Added!");
                }
                con.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Error");
            }
        });
    }
}