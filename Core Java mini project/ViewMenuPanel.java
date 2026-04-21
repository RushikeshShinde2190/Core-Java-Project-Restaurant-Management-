import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class ViewMenuPanel extends JPanel {

    public ViewMenuPanel(){

        setLayout(new BorderLayout());
        setBackground(UIStyles.BG);

        String[] cols = {"ID","Name","Price"};
        DefaultTableModel model = new DefaultTableModel(cols,0);
        JTable table = new JTable(model);

        table.setBackground(new Color(30,30,30));
        table.setForeground(Color.WHITE);
        table.setRowHeight(25);

        JTableHeader h = table.getTableHeader();
        h.setBackground(UIStyles.PRIMARY);
        h.setForeground(Color.WHITE);

        add(new JScrollPane(table), BorderLayout.CENTER);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restaurant_db","root","Root@123");

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM food_items");
            while(rs.next()){
                model.addRow(new Object[]{
                        rs.getInt(1), rs.getString(2), rs.getDouble(3)
                });
            }
            con.close();
        }catch(Exception e){}
    }
}