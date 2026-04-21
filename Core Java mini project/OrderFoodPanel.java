import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;

public class OrderFoodPanel extends JPanel {

    HashMap<String, Double> cart = new HashMap<>();

    public OrderFoodPanel(){

        setLayout(new BorderLayout());
        setBackground(UIStyles.BG);

        JPanel top = new JPanel(new GridLayout(3,2,10,10));
        top.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        top.setBackground(UIStyles.BG);

        JComboBox<String> food = UIStyles.combo();
        JTextField qty = UIStyles.input();

        JButton add = UIStyles.button("Add");
        JButton bill = UIStyles.button("Bill");

        top.add(UIStyles.label("Food"));
        top.add(food);
        top.add(UIStyles.label("Qty"));
        top.add(qty);
        top.add(add);
        top.add(bill);

        JTextArea area = new JTextArea();
        area.setBackground(new Color(30,30,30));
        area.setForeground(Color.WHITE);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        // load items
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restaurant_db","root","Root@123");

            ResultSet rs = con.createStatement().executeQuery("SELECT name FROM food_items");
            while(rs.next()) food.addItem(rs.getString(1));
            con.close();
        }catch(Exception e){}

        add.addActionListener(e -> {
            try{
                String item = (String)food.getSelectedItem();
                int q = Integer.parseInt(qty.getText());

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/restaurant_db","root","Root@123");

                PreparedStatement pst = con.prepareStatement(
                        "SELECT price FROM food_items WHERE name=?");
                pst.setString(1,item);
                ResultSet rs = pst.executeQuery();

                if(rs.next()){
                    double price = rs.getDouble(1);
                    cart.put(item, cart.getOrDefault(item,0.0)+(price*q));
                    JOptionPane.showMessageDialog(this,"Added");
                }
                con.close();
            }catch(Exception ex){}
        });

        bill.addActionListener(e -> {
            double total=0;
            area.setText("---- BILL ----\n");
            for(String k:cart.keySet()){
                area.append(k+" : ₹ "+cart.get(k)+"\n");
                total+=cart.get(k);
            }
            area.append("\nTOTAL = ₹ "+total);
        });
    }
}