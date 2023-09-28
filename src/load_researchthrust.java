import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class load_researchthrust {
    public static DefaultTableModel loadResearchThrust(DefaultTableModel thrusttableModel) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdc-rms", "root", "");
            pst = con.prepareStatement("SELECT abbreviation, name, description FROM research_thrust");
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int n = rsmd.getColumnCount();
            thrusttableModel.setColumnIdentifiers(new Object[]{"ABBREVIATION", "THRUST", "DESCRIPTION"});

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= n; i++) {
                    row.add(rs.getString(i));
                }
                thrusttableModel.addRow(row);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connections (pst, con, rs) here
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return thrusttableModel;
    }
}