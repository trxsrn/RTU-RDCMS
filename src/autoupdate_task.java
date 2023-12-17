import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class autoupdate_task {
	
	public static class StatusRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Assuming the status column is at index 3 (adjust if needed)
            int statusColumnIndex = 3;
            Object statusValue = table.getValueAt(row, statusColumnIndex);

            // Set background color based on status
            if ("To Do".equals(statusValue)) {
                rendererComponent.setBackground(Color.RED);
            } else if ("Done".equals(statusValue)) {
                rendererComponent.setBackground(Color.GREEN);
            } else if ("Pending".equals(statusValue)) {
                rendererComponent.setBackground(Color.ORANGE);
            } else {
                // Default background color
                rendererComponent.setBackground(table.getBackground());
            }

            return rendererComponent;
        }
    }
	// , JTable tasktbl
    public static DefaultTableModel loadAllTasks(DefaultTableModel TasktblModel) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdc-rms", "root", "");
            pst = con.prepareStatement("SELECT classification, task, deadline, status FROM tasks ORDER BY deadline");
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int n = rsmd.getColumnCount();
            TasktblModel.setColumnIdentifiers(new Object[]{"LEVEL", "TASK", "DEADLINE", "STATUS"});

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= n; i++) {
                    row.add(rs.getString(i));
                }
                TasktblModel.addRow(row);
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
        
//        tasktbl.getColumnModel().getColumn(3).setCellRenderer(new StatusRenderer());
        
        return TasktblModel;
    }
//, JTable tasktbl
    public static DefaultTableModel loadTasksByLevel(DefaultTableModel TasktblModel, String selectedTaskLevel) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rsSelected = null;
        ResultSet rsRemaining = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rdc-rms", "root", "");

            // 1. Query for the selected task level
            String sqlQuerySelected = "SELECT classification, task, deadline, status FROM tasks WHERE classification = ? ORDER BY deadline";
            pst = con.prepareStatement(sqlQuerySelected);
            pst.setString(1, selectedTaskLevel);
            rsSelected = pst.executeQuery();
            
            ResultSetMetaData rsmdSelected = rsSelected.getMetaData();
            int nSelected = rsmdSelected.getColumnCount();
            
            // Set column identifiers only if the model is empty
            if (TasktblModel.getRowCount() == 0) {
                TasktblModel.setColumnIdentifiers(new Object[]{"LEVEL", "TASK", "DEADLINE", "STATUS"});
            }

            while (rsSelected.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= nSelected; i++) {
                    row.add(rsSelected.getString(i));
                }
                TasktblModel.addRow(row);
            }

            // 2. Query for the remaining tasks (excluding the selected task level)
            String sqlQueryRemaining = "SELECT classification, task, deadline, status FROM tasks WHERE classification != ? ORDER BY deadline";
            pst = con.prepareStatement(sqlQueryRemaining);
            pst.setString(1, selectedTaskLevel);
            rsRemaining = pst.executeQuery();

            ResultSetMetaData rsmdRemaining = rsRemaining.getMetaData();
            int nRemaining = rsmdRemaining.getColumnCount();

            while (rsRemaining.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= nRemaining; i++) {
                    row.add(rsRemaining.getString(i));
                }
                TasktblModel.addRow(row);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connections (pst, con, rs) here
            try {
                if (rsSelected != null) {
                    rsSelected.close();
                }
                if (rsRemaining != null) {
                    rsRemaining.close();
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
        
//        tasktbl.getColumnModel().getColumn(3).setCellRenderer(new StatusRenderer());

        return TasktblModel;
    }

}
