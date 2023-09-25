import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResearchSearch {

    public static void searchResearchData(DefaultTableModel researchtableModel, String query) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdc-rms", "root", "");
            String sql = "SELECT * FROM research_summary WHERE title LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + query + "%");
            rs = stmt.executeQuery();

            // Clear the current table data
            researchtableModel.setRowCount(0);

            // Iterate through the result set and add rows to the table model
            while (rs.next()) {
                Object[] row = new Object[3]; // Assuming you have 3 colux`mns
                row[0] = rs.getString("paper_id");
                row[1] = rs.getString("title");
                row[2] = rs.getString("status");

                researchtableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources (connection, statement, result set)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}