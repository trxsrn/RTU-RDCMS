import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class load_authors {
    public static DefaultTableModel loadResearchData(DefaultTableModel authorstableModel, String paperId) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdc-rms", "root", "");
            
            // Modify the SQL query to select authors based on the paper ID
            pst = con.prepareStatement("SELECT faculty, college, department FROM research_faculty WHERE paper_id = ?");
            pst.setString(1, paperId); // Set the paper ID parameter
            rs = pst.executeQuery();

            authorstableModel.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                // Assuming the columns in your research_faculty table are named "faculty," "college," and "department"
                row.add(rs.getString("faculty"));
                row.add(rs.getString("college"));
                row.add(rs.getString("department"));
                authorstableModel.addRow(row);
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

        return authorstableModel;
    }
}
