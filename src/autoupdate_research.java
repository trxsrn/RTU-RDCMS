import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class autoupdate_research {
    public static DefaultTableModel loadResearchData(DefaultTableModel researchtableModel) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdc-rms", "root", "");
            pst = con.prepareStatement("SELECT paper_id, title, faculty, college, department, status FROM research");
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int n = rsmd.getColumnCount();
            researchtableModel.setColumnIdentifiers(new Object[]{"PAPER ID", "TITLE", "FACULTY", "College", "Department", "STATUS"});

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= n; i++) {
                    row.add(rs.getString(i));
                }
                researchtableModel.addRow(row);
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

        return researchtableModel;
    }
}