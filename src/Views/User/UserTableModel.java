package Views.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import Models.User;

public class UserTableModel extends AbstractTableModel {

    private static final int LAST_NAME_COL = 0;
    private static final int FIRST_NAME_COL = 1;

    private String[] columnNames = { "Last Name", "First Name" };

    private List<User> users;

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return users.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {

        User u = users.get(row);

        switch (col) {
            case LAST_NAME_COL:
                return u.get("name");
            case FIRST_NAME_COL:
                return u.get("surname");
            default:
                return u.get("name");
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}