import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class autoupdate_researcher {

    public static void loadResearcherData(DefaultTableModel authorstableModel, String paperId) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdc-rms", "root", "");
            pst = con.prepareStatement("SELECT `research_faculty`.faculty, `faculty`.name FROM `research_faculty` JOIN `faculty` ON `research_faculty`.faculty = `faculty`.id WHERE `research_faculty`.paper_id = ?");
            pst.setString(1, paperId); // Set the paper ID parameter
            rs = pst.executeQuery();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString(1)); // Add faculty ID to the row
                row.add(rs.getString(2)); // Add faculty name to the row
                authorstableModel.addRow(row); // Add the row to the table model
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
    }
}
