import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultySearch {

    public static void searchFacultyData(DefaultTableModel facultytableModel, String query) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdc-rms", "root", "");
            String sql = "SELECT * FROM faculty WHERE Name LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + query + "%");
            rs = stmt.executeQuery();

            // Clear the current table data
            facultytableModel.setRowCount(0);

            // Iterate through the result set and add rows to the table model
            while (rs.next()) {
                Object[] row = new Object[5]; // Assuming you have 3 colux`mns
                row[0] = rs.getString("ID");
                row[1] = rs.getString("Name");
                row[2] = rs.getString("AFFILIATION");
                row[3] = rs.getString("College");
                row[4] = rs.getString("Department");

                facultytableModel.addRow(row);
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