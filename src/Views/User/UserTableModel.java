package Views.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import Models.User;
import Models.UsersRole;

/**
 * Model for Users List.
 */
public class UserTableModel extends AbstractTableModel {

    private static final int LAST_NAME_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int EMAIL_COL = 2;
    private static final int LOGIN_COL = 3;
    private static final int ROLE_COL = 4;
    private static final int CREATED_COL = 5;

    private String[] columnNames = { "Imie", "Nazwisko", "Email", "Login", "Grupa", "Utworzony" };

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
            case EMAIL_COL:
                return u.get("email");
            case LOGIN_COL:
                return u.get("login");
            case ROLE_COL:
                return u.parent(UsersRole.class).get("displayed");
            case CREATED_COL:
                return u.get("created_at");
            default:
                return "-";
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}